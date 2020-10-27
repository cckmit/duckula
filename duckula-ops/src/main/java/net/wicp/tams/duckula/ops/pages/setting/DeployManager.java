package net.wicp.tams.duckula.ops.pages.setting;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeployExample;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeployExample.Criteria;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.ajax.IAjax;

@Slf4j
public class DeployManager {
	@Inject
	protected RequestGlobals requestGlobals;

	@Inject
	protected Request request;

	@Inject
	private IReq req;

	@Inject
	private IAjax ajax;
	@Inject
	private CommonDeployMapper commonDeployMapper;

	public TextStreamResponse onQuery() {
		//ajax.req(key, params);		
		CommonDeployExample commonDeployExample = new CommonDeployExample();
		Criteria criteria = commonDeployExample.createCriteria();
		List<CommonDeploy> allDeploys = commonDeployMapper.selectByExample(commonDeployExample);
		String retstr = EasyUiAssist.getJsonForGridAlias(allDeploys, allDeploys.size());
		return TapestryAssist.getTextStreamResponse(retstr);
	}

	public TextStreamResponse onSave() {
		final CommonDeploy commonDeploy = TapestryAssist.getBeanFromPage(CommonDeploy.class, requestGlobals);

		if (commonDeploy.getId() == null) {
			commonDeployMapper.insert(commonDeploy);
		} else {
			commonDeployMapper.updateById(commonDeploy);
		}
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

}