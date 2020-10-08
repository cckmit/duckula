package net.wicp.tams.app.duckula.controller.service.deploy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kubernetes.client.openapi.models.V1ConfigMap;
import net.wicp.tams.app.duckula.controller.bean.models.CommonInstance;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.ConfigItem;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.config.constant.DeployType;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.dao.CommonInstanceMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.app.duckula.controller.service.K8sService;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.beans.Host;

/***
 * 服务名要与DeployType同名
 * 
 * @author Andy.zhou
 *
 */
@Service("k8s")
public class DeployK8s implements IDeploy {
	@Autowired
	private K8sService k8sService;
	@Autowired
	private CommonTaskMapper commonTaskMapper;
	@Autowired
	private CommonMiddlewareMapper commonMiddlewareMapper;
	@Autowired
	private CommonInstanceMapper commonInstanceMapper;

	@Override
	public Result checkExit(Long deployid, CommandType taskType, Long taskId) {
		String configName = null;
		switch (taskType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			configName = taskType.formateConfigName(selectTask.getName());
			break;
		default:
			break;
		}
		try {
			V1ConfigMap selectConfigMap = k8sService.selectConfigMap(deployid, configName);
			if (selectConfigMap == null) {
				return Result.getError("查找失败");
			} else {
				return Result.getSuc();
			}
		} catch (Throwable e) {
			return Result.getError("查找异常:" + e.getMessage());
		}
	}

	@Override
	public Result addConfig(Long deployid, CommandType commandType, Long taskId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Long middlewareId = null;
		String configName = null;
		Long instanceId = null;
		switch (commandType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			params.putAll(CommandType.proTaskConfig(selectTask));// 默认配置
			configName = commandType.formateConfigName(selectTask.getName());
			middlewareId = selectTask.getMiddlewareId();
			instanceId = selectTask.getInstanceId();
			break;
		default:
			break;
		}
		CommonMiddleware middleware = commonMiddlewareMapper.selectById(middlewareId);
		MiddlewareType middlewareType = MiddlewareType.valueOf(middleware.getMiddlewareType());
		// 配置插件
		Map<String, Object> pluginConfig = middlewareType.proPluginConfig(commandType, middleware.getVersion());
		params.putAll(pluginConfig);
		// 配置目标中间件
		Map<String, Object> proConfig = middlewareType.proConfig(middleware);
		params.putAll(proConfig);
		// 配置监听实例,如consumer可能就没有这个实例
		if (instanceId != null) {
			CommonInstance commonInstance = commonInstanceMapper.selectById(instanceId);
			params.putAll(configInstall(commonInstance));
		}

		String propStr = DeployType.formateConfig(DeployType.k8s, commandType, params);
		k8sService.deployConfigmap(deployid, configName, propStr);
		return Result.getSuc();
	}

	@Override
	public void start(Long deployid, CommandType taskType, Long taskId, boolean isDebug) {
		if (!checkExit(deployid, taskType, taskId).isSuc()) {
			addConfig(deployid, taskType, taskId);
		}
		String configName = null;
		Map<String, Object> params = new HashMap<String, Object>();
		switch (taskType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			configName = taskType.formateTaskName(selectTask.getName());
			params.put(ConfigItem.task_name, configName);
			params.put(ConfigItem.task_version, selectTask.getVersion());
			params.put(ConfigItem.task_debug, isDebug);
			params.put(ConfigItem.configmap_name, taskType.formateConfigName(selectTask.getName()));
			// 处理中间件的hosts
			CommonMiddleware middleware = commonMiddlewareMapper.selectById(selectTask.getMiddlewareId());
			List<Host> jsonToHosts = Host.jsonToHosts(middleware.getHostsconfig());
			params.put(ConfigItem.task_hosts, jsonToHosts);
			break;
		default:
			break;
		}
		k8sService.deployTask(deployid, params);
	}

}
