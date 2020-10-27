package net.wicp.tams.duckula.ops.pages.setting;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonVersion;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonVersionMapper;
import net.wicp.tams.app.duckula.controller.service.DeployService;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.common.callback.IConvertValue;
import net.wicp.tams.common.callback.impl.convertvalue.ConvertValueEnum;
import net.wicp.tams.common.constant.dic.YesOrNo;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.WebTools;
import net.wicp.tams.duckula.ops.ajax.IAjax;

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
	@Inject
	private CommonVersionMapper commonVersionMapper;
	@Inject
	private DeployService deployService;

	public TextStreamResponse onQuery() {
		// ajax.req(key, params);
		QueryWrapper<CommonDeploy> queryWrapper = new QueryWrapper<CommonDeploy>();
		Page<CommonDeploy> selectPage = commonDeployMapper.selectPage(WebTools.buildPage(request), queryWrapper);
		String retstr = EasyUiAssist.getJsonForGrid(selectPage.getRecords(),
				new String[] { "id", "name", "deploy", "env", "namespace", "host", "port", "pwdDuckula", "isInit",
						"isDefault", "imagegroup", "version", "remark", "isInit,isInit2" },
				new IConvertValue[] { null, null, null, null, null, null, null, null, null, null, null, null, null,
						new ConvertValueEnum(YesOrNo.class) },
				selectPage.getTotal());
		return TapestryAssist.getTextStreamResponse(retstr);
	}

	public TextStreamResponse onSave() {
		final CommonDeploy commonDeploy = TapestryAssist.getBeanFromPage(CommonDeploy.class, requestGlobals);

		if (commonDeploy.getId() == null) {
			commonDeployMapper.insert(commonDeploy);
		} else {
			commonDeployMapper.updateByPrimaryKeySelective(commonDeploy);
		}
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDel() {
		String id = request.getParameter("id");
		commonDeployMapper.deleteById(id);
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onInitServer() {
		final CommonDeploy commonDeploy = TapestryAssist.getBeanFromPage(CommonDeploy.class, requestGlobals);
		String pwd = request.getParameter("pwd");
		Result result = deployService.initHost(commonDeploy, pwd);
		if (result.isSuc()) {
			commonDeploy.setIsInit(YesOrNo.yes.name());
			commonDeployMapper.updateByPrimaryKeySelective(commonDeploy);
		}
		return TapestryAssist.getTextStreamResponse(result);
	}

	public TextStreamResponse onUpServer() {
		final CommonDeploy commonDeploy = TapestryAssist.getBeanFromPage(CommonDeploy.class, requestGlobals);
		CommonVersion commonVersionNew = null;
		try {
			int versionNew = Integer.parseInt(request.getParameter("versionNew"));
			commonVersionNew = commonVersionMapper.selectById(versionNew);
			if (commonVersionNew == null) {
				return TapestryAssist.getTextStreamResponse(Result.getError("没有这个版本"));
			}
		} catch (Exception e) {
			return TapestryAssist.getTextStreamResponse(Result.getError("版本需要数字"));
		}

		Result result = deployService.upgradeVersion(commonDeploy, commonVersionNew);
		if (result.isSuc()) {
			commonDeploy.setVersion(commonVersionNew.getId());
			commonDeployMapper.updateByPrimaryKeySelective(commonDeploy);
		}
		return TapestryAssist.getTextStreamResponse(result);
	}

}