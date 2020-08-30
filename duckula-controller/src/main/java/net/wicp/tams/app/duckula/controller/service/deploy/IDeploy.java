package net.wicp.tams.app.duckula.controller.service.deploy;

import net.wicp.tams.app.duckula.controller.config.constant.TaskType;
import net.wicp.tams.common.Result;

public interface IDeploy {
	public Result checkExit(Long deployid, TaskType taskType, Long taskId);

	public Result addConfig(Long deployid, TaskType taskType, Long taskId);

	public void start(Long deployid, TaskType taskType, Long taskId);
}
