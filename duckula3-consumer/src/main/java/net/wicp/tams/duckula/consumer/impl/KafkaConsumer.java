package net.wicp.tams.duckula.consumer.impl;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.binlog.alone.Config;
import net.wicp.tams.common.binlog.alone.DuckulaAssit;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEvent.Builder;
import net.wicp.tams.common.binlog.alone.binlog.bean.Rule;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleFilter;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleManager;
import net.wicp.tams.common.binlog.alone.binlog.listener.AbsConsumerListener;
import net.wicp.tams.common.binlog.alone.constant.FilterPattern;
import net.wicp.tams.common.constant.JvmStatus;
import net.wicp.tams.common.jdbc.DruidAssit;
import net.wicp.tams.common.kafka.IConsumer;
import net.wicp.tams.common.thread.threadlocal.PerthreadManager;
import net.wicp.tams.duckula.consumer.jmx.ConsumerControl;

@Slf4j
public class KafkaConsumer implements IConsumer<byte[]> {

	private final AbsConsumerListener consumerListener;

	private final RuleManager ruleManager;

	private final boolean isSplit;// 是否单条发送?

	public KafkaConsumer(AbsConsumerListener consumerListener, String ruleStr, boolean isSplit) {
		this.consumerListener = consumerListener;
		this.ruleManager = new RuleManager(ruleStr);
		this.isSplit = isSplit;
		initMbean();
		addTimer();
		// 设置数据源
		DataSource dataSourceNoConf = getDataSource(Config.globleDatasourceName);
		PerthreadManager.getInstance().createValue(Config.globleDatasourceName, DataSource.class).set(dataSourceNoConf);
		for (int i = 0; i < this.ruleManager.getRules().size(); i++) {
			consumerListener.doInit(this.ruleManager.getRules().get(i), i);// 初始化consumer,Es会检查index并自动创建
		}
	}

	@Override
	public Result doWithRecords(List<ConsumerRecord<String, byte[]>> consumerRecords) {
		log.info("begin");
		for (ConsumerRecord<String, byte[]> consumerRecord : consumerRecords) {
			try {
				Builder duckulaEventBuilder = DuckulaAssit.parse(consumerRecord.value()).toBuilder();
				Rule rule = ruleManager.findRule(duckulaEventBuilder.getDb(), duckulaEventBuilder.getTb());
				String addProp = StringUtil.hasNull(duckulaEventBuilder.getAddProp(), "_global");
				if (duckulaEventBuilder.getIsError()) {// 是单条发送或是幂等，无需顺序
					Connection conn = getDataSource(addProp).getConnection();
					// TODO 查一下数据 ,补全数据
				}
				// 过滤处理
				for (RuleFilter filterRulePo : rule.getFilterRules()) {
					if (filterRulePo.getFilterPattern() == FilterPattern.sql) {// 初始化连接池
						DataSource dataSourceNoConf = getDataSource(addProp);
						PerthreadManager.getInstance().createValue(Config.CurThreadDatasourceName, DataSource.class)
								.set(dataSourceNoConf);
					}
					filterRulePo.getFilterPattern().getFilter().doFilter(duckulaEventBuilder, rule, filterRulePo);
					if (duckulaEventBuilder.getItemsCount() == 0) {
						log.info("---------------------过滤后没有处理的数据-----------------------------");
						break;
					}
				}
				// 发送处理
				if (duckulaEventBuilder.getItemsCount() > 0) {
					consumerListener.doBui(rule, duckulaEventBuilder.build(), this.isSplit);// 它会重试发送
				}
			} catch (Exception e) {
				log.error("parse error", e);
				LoggerUtil.exit(JvmStatus.s15);
			}
		}
		return Result.getSuc();
	}

	private void initMbean() {
		try {
			ConsumerControl control = new ConsumerControl();
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			mbs.registerMBean(control, new ObjectName("Commons:name=consumerBean"));
			log.info("----------------------MBean注册成功-------------------------------------");
		} catch (Exception e) {
			log.error("启动Mbean失败", e);
		}
	}

	private void addTimer() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// TODO
			}
		}, 10, 3, TimeUnit.SECONDS);
	}

	private DataSource getDataSource(String addProp) {
		String sourceKey = "drds:" + addProp;
		DataSource retDataSource;
		if (!DruidAssit.getDataSourceMap().containsKey(sourceKey)) {
			Properties jdbcprops = Conf.getPreToProp("common.binlog.alone.plugin.jdbc." + addProp, true);
			retDataSource = DruidAssit.getDataSourceNoConf(sourceKey, jdbcprops);
		} else {
			retDataSource = DruidAssit.getDataSource(sourceKey);
		}
		return retDataSource;
	}

}
