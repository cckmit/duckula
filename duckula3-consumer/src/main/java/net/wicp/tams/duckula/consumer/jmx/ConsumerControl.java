package net.wicp.tams.duckula.consumer.jmx;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.constant.JvmStatus;

@Data
@Slf4j
public class ConsumerControl implements ConsumerControlMBean {


	public void stop() {
		log.info("通过MBean服务停止服务");
		LoggerUtil.exit(JvmStatus.s15);
	}

}
