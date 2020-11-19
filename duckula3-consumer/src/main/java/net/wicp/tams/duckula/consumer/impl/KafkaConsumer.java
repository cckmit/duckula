package net.wicp.tams.duckula.consumer.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.binlog.alone.DuckulaAssit;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEvent;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEventItem;
import net.wicp.tams.common.kafka.IConsumer;

@Slf4j
public class KafkaConsumer<T> implements IConsumer<byte[]> {

	public KafkaConsumer() {
		Properties props = new Properties();
		props.put("common.kafka.common.bootstrap.servers", "test-kafka-001.taimei.com:9092");
		Conf.overProp(props);
	}

	@Override
	public Result doWithRecords(List<ConsumerRecord<String, byte[]>> consumerRecords) {
		log.info("begin");
		for (ConsumerRecord<String, byte[]> consumerRecord : consumerRecords) {
			try {
				DuckulaEvent parse = DuckulaAssit.parse(consumerRecord.value());
				
				List<DuckulaEventItem> itemsList = parse.getItemsList();
				Map<String, String> valueMap = DuckulaAssit.getValueMap(parse, 0);
				log.info("aaa"+valueMap);
			} catch (Exception e) {
				log.error("parse error", e);
			}

		}
		return null;
	}

}
