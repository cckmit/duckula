package net.wicp.tams.app.duckula.controller.service.deploy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kubernetes.client.openapi.models.V1ConfigMap;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.config.constant.DeployType;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.app.duckula.controller.service.K8sService;
import net.wicp.tams.common.Result;

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
	private CommonDeployMapper commonDeployMapper;

	@Override
	public Result checkExit(Long deployid, CommandType taskType, Long taskId) {
		String configName = null;
		switch (taskType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			configName = String.format("task-%s", selectTask.getName());// 最大为64
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
		Map<String, String> params = new HashMap<String, String>();
		params.putAll(commandType.getDefaultconfig());// 默认配置
		Long middlewareId = null;
		String configName = null;
		switch (commandType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			configName = String.format("task-%s", selectTask.getName());// 最大为64
			middlewareId = selectTask.getMiddlewareId();
			break;
		default:
			break;
		}
		CommonMiddleware middleware = commonMiddlewareMapper.selectById(middlewareId);
		MiddlewareType middlewareType = MiddlewareType.valueOf(middleware.getMiddlewareType());
		String[] verPluginByVersion = middlewareType.getVerPluginByVersion(middleware.getVersion());
		params.put("common.binlog.alone.binlog.global.conf.listener",
				commandType == CommandType.task ? verPluginByVersion[1] : verPluginByVersion[2]);// 监听器
		// 配置目标中间件
		Map<String, String> proConfig = middlewareType.proConfig(middleware);
		params.putAll(proConfig);
		String propStr = DeployType.formateConfig(DeployType.k8s, commandType, params);
		String configname = String.format("conf-%s", configName.substring(5));
		k8sService.deployConfigmap(deployid, configname, propStr);
		return Result.getSuc();
	}

	@Override
	public void start(Long deployid, CommandType taskType, Long taskId) {
		String configName = null;
		Map<String, String> params = new HashMap<String, String>();
		switch (taskType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			configName = taskType.formateTaskName(selectTask.getName());
			params.put("name", configName);
			break;
		default:
			break;
		}
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		DeployType valueOf = DeployType.valueOf(commonDeploy.getDeploy());

		k8sService.deployTask(deployid, params);
	}

}
