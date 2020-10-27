package net.wicp.tams.duckula.ops.pages.setting;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.WebTools;
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
		// ajax.req(key, params);
		QueryWrapper<CommonDeploy> queryWrapper = new QueryWrapper<CommonDeploy>();
		Page<CommonDeploy> selectPage = commonDeployMapper.selectPage(WebTools.buildPage(request), queryWrapper);
		String retstr = EasyUiAssist.getJsonForGrid(
				selectPage.getRecords(), new String[] { "id", "name", "deploy", "env", "namespace", "host", "port",
						"pwdDuckula", "isInit", "isDefault", "imagegroup", "version", "remark" },
				selectPage.getTotal());
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