package net.wicp.tams.app.duckula.controller.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.util.Yaml;
import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.config.ConfigItem;
import net.wicp.tams.app.duckula.controller.config.ExceptDuckula;
import net.wicp.tams.app.duckula.controller.config.k8s.ApiClientManager;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.common.apiext.FreemarkUtil;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.exception.ExceptAll;
import net.wicp.tams.common.exception.ProjectExceptionRuntime;

/***
 * k8s接口全部在此定义
 * 
 * @author Andy.zhou
 *
 */
@Service
@Slf4j
public class K8sService {

	@Autowired
	private CommonDeployMapper commonDeployMapper;

	public V1Job deployDump(Long deployid, Map<String, String> params) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		ApiClient apiClient = getApiClient(commonDeploy);
		try {
			String context = IOUtil.slurp(IOUtil.fileToInputStream("/job.yaml", K8sService.class));
			String result = FreemarkUtil.getInst().doProcessByTemp(context, params);
			V1Job yamlSvc = (V1Job) Yaml.load(result);
			BatchV1Api batchV1Api = new BatchV1Api(apiClient);
			V1Job v1Job = batchV1Api.createNamespacedJob(commonDeploy.getNamespace(), yamlSvc, "true", null, null);
			return v1Job;
		} catch (Exception e) {
			log.error("创建job失败", e);
			throw new RuntimeException(e);
		}
	}

	public V1Deployment deployTask(Long deployid, Map<String, String> params) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		ApiClient apiClient = getApiClient(commonDeploy);
		try {
			String context = IOUtil.slurp(IOUtil.fileToInputStream("/deploy/k8s/deployment.yaml", K8sService.class));
			String result = FreemarkUtil.getInst().doProcessByTemp(context, params);
			V1Deployment yamlSvc = (V1Deployment) Yaml.load(result);
			AppsV1Api appsV1Api = new AppsV1Api(apiClient);
			V1Deployment v1Deployment = appsV1Api.createNamespacedDeployment(commonDeploy.getNamespace(), yamlSvc,
					"true", null, null);
			return v1Deployment;
		} catch (ApiException e) {
			if ("Conflict".equals(e.getMessage())) {
				throw new ProjectExceptionRuntime(ExceptAll.k8s_deploy_conflict);
			} else {
				throw new ProjectExceptionRuntime(ExceptAll.k8s_api_other, e.getMessage());
			}
		} catch (Exception e) {
			log.error("部署task失败", e);
			throw new ProjectExceptionRuntime(ExceptDuckula.duckula_deploy_excetion, e.getMessage());
		}
	}

	/**
	 * 部署configmap
	 * 
	 * @param deployid      部署id
	 * @param configmapName configmap名称
	 * @param params        key：文件名 value：文件内容，不需要考虑空格,暂时只有一个
	 * @return
	 */
	public V1ConfigMap deployConfigmap(Long deployid, String configMapName, String configmapStr) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(ConfigItem.configmap_name, configMapName);
		// 保证第一行为非空字符，为了避免yaml语法检查失败
		if (!configmapStr.startsWith("    ")) {
			configmapStr = configmapStr.replace("\n", "\n    ");// 加4个空格用于完成map格式
		} else {
			configmapStr = configmapStr.substring(4);
		}
		params.put(ConfigItem.configmap_filecontext, configmapStr);
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		ApiClient apiClient = getApiClient(commonDeploy);
		try {
			String context = IOUtil.slurp(IOUtil.fileToInputStream("/deploy/k8s/configMap.yaml", K8sService.class));
			String result = FreemarkUtil.getInst().doProcessByTemp(context, params);
			V1ConfigMap yamlSvc = (V1ConfigMap) Yaml.load(result);
			CoreV1Api coreV1Api = new CoreV1Api(apiClient);
			V1ConfigMap v1ConfigMap = coreV1Api.createNamespacedConfigMap(commonDeploy.getNamespace(), yamlSvc, "true",
					null, null);
			return v1ConfigMap;
		} catch (Exception e) {
			log.error("创建V1ConfigMap失败", e);
			throw new RuntimeException(e);
		}
	}

	/***
	 * 查询ConfigMap
	 * 
	 * @param deployid
	 * @return
	 */
	public V1ConfigMap selectConfigMap(Long deployid, String configName) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		ApiClient apiClient = getApiClient(commonDeploy);
		CoreV1Api coreV1Api = new CoreV1Api(apiClient);
		String pretty = null;
		Boolean exact = null;
		Boolean export = null;
		try {
			V1ConfigMap v1ConfigMap = coreV1Api.readNamespacedConfigMap(configName, commonDeploy.getNamespace(), pretty,
					exact, export);
			return v1ConfigMap;
		} catch (Exception e) {
			log.error("查询V1ConfigMap失败", e);
			throw new RuntimeException(e);
		}
	}

	public ApiClient getApiClient(Long deployid) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		return getApiClient(commonDeploy);
	}

	public ApiClient getApiClient(CommonDeploy commonDeploy) {
		if (commonDeploy == null || StringUtil.isNull(commonDeploy.getConfig())) {
			throw new RuntimeException("没有得到k8s相关配置");
		}
		ApiClient apiClient = ApiClientManager.getApiClient(String.valueOf(commonDeploy.getId()),
				commonDeploy.getConfig());
		return apiClient;
	}
}
