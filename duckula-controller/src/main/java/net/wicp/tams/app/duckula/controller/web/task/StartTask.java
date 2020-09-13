package net.wicp.tams.app.duckula.controller.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.app.duckula.controller.service.deploy.IDeploy;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.connector.beans.CusDynaBean;
import net.wicp.tams.common.connector.executor.IBusiApp;
import net.wicp.tams.common.exception.ProjectException;
import net.wicp.tams.common.spring.autoconfig.SpringAssit;

@Service(value = "task.StartTask")
public class StartTask implements IBusiApp {

	@Autowired
	private CommonTaskMapper commonTaskMapper;
	@Autowired
	private CommonDeployMapper commonDeployMapper;

	@Override
	public CusDynaBean exe(CusDynaBean inputBean, CusDynaBean outBeanOri) throws ProjectException {
		long taskId = Long.parseLong(inputBean.getStrValueByName("taskId"));
		CommonTask commonTask = commonTaskMapper.selectById(taskId);
		CommonDeploy commonDeploy = commonDeployMapper.selectById(commonTask.getDeployId());
		IDeploy deploy = (IDeploy) SpringAssit.context.getBean(commonDeploy.getDeploy());
		deploy.start(commonDeploy.getId(), CommandType.task, taskId);
		Result ret = Result.getSuc(commonTask.getName());
		outBeanOri.setResult(ret);
		return outBeanOri;
	}
}
