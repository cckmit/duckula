package net.wicp.tams.app.duckula.controller.web.task;

import org.springframework.stereotype.Service;

import net.wicp.tams.common.Result;
import net.wicp.tams.common.connector.beans.CusDynaBean;
import net.wicp.tams.common.connector.executor.IBusiApp;
import net.wicp.tams.common.exception.ProjectException;

@Service(value = "task.QueryTask")
public class QueryTask implements IBusiApp {

	//@Autowired
	//private CommonTaskMapper commonTaskMapper;

	@Override
	public CusDynaBean exe(CusDynaBean inputBean, CusDynaBean outBeanOri) throws ProjectException {
		String name = inputBean.getStrValueByName("name");
		//CommonTask commonTask = commonTaskMapper.selectByPrimaryKey(1);
		Result ret = Result.getSuc("");
		outBeanOri.setResult(ret);
		outBeanOri.set("ret1", "hello " + name);
		return outBeanOri;
	}
}
