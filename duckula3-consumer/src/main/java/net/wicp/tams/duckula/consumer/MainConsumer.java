package net.wicp.tams.duckula.consumer;

import net.wicp.tams.common.kafka.KafkaConsumerGroup;
import net.wicp.tams.common.kafka.KafkaConsumerGroupB;
import net.wicp.tams.duckula.consumer.impl.KafkaConsumer;

public class MainConsumer {

	public static void main(String[] args) {
		KafkaConsumer<byte[]> doConsumer = new KafkaConsumer<>();
		KafkaConsumerGroup<byte[]> group = new KafkaConsumerGroupB("abc", "gvp-base", doConsumer, 1);
		group.start();
	}

}
