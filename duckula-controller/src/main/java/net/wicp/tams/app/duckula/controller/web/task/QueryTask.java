package net.wicp.tams.app.duckula.controller.web.task;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.connector.beans.CusDynaBean;
import net.wicp.tams.common.connector.executor.IBusiApp;
import net.wicp.tams.common.exception.ProjectException;

@Service(value = "task.QueryTask")
public class QueryTask implements IBusiApp {

	@Autowired
	private CommonTaskMapper commonTaskMapper;

	@Override
	public CusDynaBean exe(CusDynaBean inputBean, CusDynaBean outBeanOri) throws ProjectException {
		Subject loginUser = SecurityUtils.getSubject();
		System.out.println(loginUser.getPrincipal());
		String name = inputBean.getStrValueByName("name");
		CommonTask commonTask = commonTaskMapper.selectById(1);
		CommonTask selectByPrimaryKey = commonTaskMapper.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey.getName());
		Result ret = Result.getSuc(commonTask.getName());
		outBeanOri.setResult(ret);
		outBeanOri.set("ret1", "hello " + name);
		return outBeanOri;
	}
}
