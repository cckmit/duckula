package net.wicp.tams.app.duckula.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.config.constant.DeployType;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.service.deploy.IDeploy;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.spring.autoconfig.SpringAssit;

@Service
@Slf4j
public class DeployService {
	@Autowired
	private CommonDeployMapper commonDeployMapper;

	public void deployTask(CommonTask commonTask) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(commonTask.getDeployId());
		DeployType deployType = DeployType.valueOf(commonDeploy.getDeploy());
		IDeploy deploy = (IDeploy) SpringAssit.context.getBean(deployType.name());
		Result checkExit = deploy.checkExit(commonTask.getDeployId(), CommandType.task, commonTask.getId());
		if (!checkExit.isSuc()) {
			deploy.addConfig(commonTask.getDeployId(), CommandType.task, commonTask.getId());
		}
		//deploy.start();
		log.info("the task:{} start sucess", commonTask.getName());
	}
}
