package net.wicp.tams.app.duckula.controller.service.deploy;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Case;

import net.wicp.tams.app.duckula.controller.bean.models.CommonInstance;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.common.Result;

public interface IDeploy {
	public Result checkExit(Long deployid, CommandType taskType, Long taskId);

	public Result addConfig(Long deployid, CommandType taskType, Long taskId);
	
	public Result deleteConfig(Long deployid, CommandType commandType, Long taskId);

	public Result start(Long deployid, CommandType taskType, Long taskId, boolean isDebug);
	
	public Result stop(Long deployid, CommandType taskType, Long taskId);

	// 查询任务的相关状态
	public String queryStatus(Long deployid, CommandType taskType, Long taskId);

	// 配置监听实例
	public default Map<String, Object> configInstall(CommandType taskType,CommonInstance commonInstance) {
		Map<String, Object> tempmap = new HashMap<String, Object>();
		switch (taskType) {
		case task:
			tempmap.put("common.binlog.alone.binlog.global.conf.host", commonInstance.getHost());
			tempmap.put("common.binlog.alone.binlog.global.conf.port", commonInstance.getPort());
			tempmap.put("common.binlog.alone.binlog.global.conf.username", commonInstance.getUsername());
			tempmap.put("common.binlog.alone.binlog.global.conf.password", commonInstance.getPassword());
			tempmap.put("common.binlog.alone.binlog.global.conf.rds", "false");// 写死为false,不理rds			
			break;
		case dump:
			tempmap.put("common.binlog.alone.dump.global.pool.host", commonInstance.getHost());
			tempmap.put("common.binlog.alone.dump.global.pool.port", commonInstance.getPort());
			tempmap.put("common.binlog.alone.dump.global.pool.username", commonInstance.getUsername());
			tempmap.put("common.binlog.alone.dump.global.pool.password", commonInstance.getPassword());
			//tempmap.put("common.binlog.alone.binlog.global.conf.rds", "false");// 写死为false,不理rds  dump不认
			break;
		default:
			break;
		}
		
		
		return tempmap;
	}
}
