package net.wicp.tams.duckula.consumer;

import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.binlog.alone.binlog.listener.AbsConsumerListener;
import net.wicp.tams.common.constant.JvmStatus;
import net.wicp.tams.common.kafka.KafkaConsumerGroup;
import net.wicp.tams.common.kafka.KafkaConsumerGroupB;
import net.wicp.tams.duckula.consumer.impl.KafkaConsumer;

@Slf4j
public class MainConsumer {
	public static void main(String[] args) {
		String rootDir = System.getenv("DUCKULA3_DATA");
		String relaPath = "/conf/configmap.properties";
		if (ArrayUtils.isNotEmpty(args)) {
			relaPath = String.format("/conf/%s.properties", args[0]);
		}
		log.info("使用配置文件:{}", relaPath);
		// Properties props = IOUtil.fileToProperties(new
		// File(IOUtil.mergeFolderAndFilePath(rootDir, relaPath)));
		Properties props = IOUtil.fileToProperties("/consumer-demo.properties", MainConsumer.class);
		Conf.overProp(props);

		AbsConsumerListener consumerListener = null;
		try {
			ClassLoader classloadertrue = Conf.pluginClassLoader("common.binlog.alone.consumer.global.busiPluginDir");
			consumerListener = (AbsConsumerListener) classloadertrue
					.loadClass(Conf.get("common.binlog.alone.consumer.global.busiSender")).newInstance();
		} catch (Exception e) {
			log.error("错误的发送者", e);
			LoggerUtil.exit(JvmStatus.s15);
		}
		KafkaConsumer doConsumer = new KafkaConsumer(consumerListener,
				Conf.get("common.binlog.alone.consumer.global.rule"),
				Conf.getBoolean("common.binlog.alone.consumer.global.split"));
		KafkaConsumerGroup<byte[]> group = new KafkaConsumerGroupB(
				Conf.get("common.binlog.alone.consumer.global.groupId"),
				Conf.get("common.binlog.alone.consumer.global.topic"), doConsumer,
				Conf.getInt("common.binlog.alone.consumer.global.hosts"));
		group.start();
	}
}
