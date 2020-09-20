package net.wicp.tams.app.duckula.controller.service.deploy;

import java.util.HashMap;
import java.util.Map;

import net.wicp.tams.app.duckula.controller.bean.models.CommonInstance;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.common.Result;

public interface IDeploy {
	public Result checkExit(Long deployid, CommandType taskType, Long taskId);

	public Result addConfig(Long deployid, CommandType taskType, Long taskId);

	public void start(Long deployid, CommandType taskType, Long taskId,boolean isDebug);

	// 配置监听实例
	public default Map<String, Object> configInstall(CommonInstance commonInstance) {
		Map<String, Object> tempmap = new HashMap<String, Object>();
		tempmap.put("common.binlog.alone.binlog.global.conf.host", commonInstance.getHost());
		tempmap.put("common.binlog.alone.binlog.global.conf.port", commonInstance.getPort());
		tempmap.put("common.binlog.alone.binlog.global.conf.username", commonInstance.getUsername());
		tempmap.put("common.binlog.alone.binlog.global.conf.password", commonInstance.getPassword());
		tempmap.put("common.binlog.alone.binlog.global.conf.rds", commonInstance.getIsRds());
		return tempmap;
	}
}
