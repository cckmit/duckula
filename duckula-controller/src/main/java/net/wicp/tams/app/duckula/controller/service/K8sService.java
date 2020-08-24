package net.wicp.tams.app.duckula.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kubernetes.client.openapi.ApiClient;
import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.config.k8s.ApiClientManager;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.common.apiext.StringUtil;

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

	public void deploy(Long deployid) {
		ApiClient apiClient = getApiClient(deployid);

	}

	public ApiClient getApiClient(Long deployid) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(deployid);
		if (deployid == null || StringUtil.isNull(commonDeploy.getConfig())) {
			throw new RuntimeException("没有得到k8s相关配置");
		}
		ApiClient apiClient = ApiClientManager.getApiClient(String.valueOf(commonDeploy.getId()),
				commonDeploy.getConfig());
		return apiClient;
	}
}
