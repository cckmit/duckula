package net.wicp.tams.duckula.consumer.impl;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.binlog.alone.DuckulaAssit;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEvent;
import net.wicp.tams.common.binlog.alone.binlog.bean.Rule;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleManager;
import net.wicp.tams.common.binlog.alone.binlog.listener.AbsConsumerListener;
import net.wicp.tams.common.constant.JvmStatus;
import net.wicp.tams.common.jdbc.DruidAssit;
import net.wicp.tams.common.kafka.IConsumer;
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
	}

	@Override
	public Result doWithRecords(List<ConsumerRecord<String, byte[]>> consumerRecords) {
		log.info("begin");
		for (ConsumerRecord<String, byte[]> consumerRecord : consumerRecords) {
			try {
				DuckulaEvent duckulaEvent = DuckulaAssit.parse(consumerRecord.value());
				Rule rule = ruleManager.findRule(duckulaEvent.getDb(), duckulaEvent.getTb());
				if (duckulaEvent.getIsError()) {// 是单条发送或是幂等，无需顺序
					String addProp = StringUtil.hasNull(duckulaEvent.getAddProp(), "default");
					Connection conn = getConn(addProp);
					// TODO 查一下数据 ,补全数据
				}
				consumerListener.doBui(rule, duckulaEvent, this.isSplit);// 它会重试发送
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

	private Connection getConn(String addProp) throws SQLException {
		Connection connection = null;
		if (StringUtil.isNotNull(addProp)) {
			String sourceKey = "drds:" + addProp;
			if (!DruidAssit.getDataSourceMap().containsKey(sourceKey)) {
				Properties jdbcprops = Conf.getPreToProp("common.binlog.alone.consumer.addProp." + addProp, true);
				connection = DruidAssit.getDataSourceNoConf(sourceKey, jdbcprops).getConnection();
			} else {
				connection = DruidAssit.getDataSource(sourceKey).getConnection();
			}

		} else {
			connection = DruidAssit.getConnection();
		}
		return connection;
	}

}
