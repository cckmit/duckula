package net.wicp.tams.app.duckula.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.service.MiddlewareService;
import net.wicp.tams.common.connector.beans.CusDynaBean;
import net.wicp.tams.common.connector.executor.IBusiApp;
import net.wicp.tams.common.exception.ProjectException;

/***
 * 中间件查询
 * 
 * @author Andy.zhou
 *
 */
@Service("MiddlewareQuery")
public class MiddlewareQuery implements IBusiApp {

	@Autowired
	private MiddlewareService middlewareService;

	@Override
	public CusDynaBean exe(CusDynaBean inputBean, CusDynaBean outBeanOri) throws ProjectException {
		MiddlewareType middlewareType = inputBean.getByType(MiddlewareType.class, "middlewareType");
		String version = inputBean.getStrValueByName("version");
		List<CommonMiddleware> middlewarelist = middlewareService.queryMiddleware(middlewareType, version);
		outBeanOri.set("middlewarelist", middlewarelist.toArray(new CommonMiddleware[middlewarelist.size()]));
		return outBeanOri;
	}

}
