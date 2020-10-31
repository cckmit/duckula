package net.wicp.tams.duckula.ops.pages.setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.common.apiext.json.JSONUtil;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.WebTools;

public class MiddlewareManager {
	@Inject
	protected RequestGlobals requestGlobals;

	@Inject
	protected Request request;

	@Inject
	private IReq req;

	@Inject
	private CommonMiddlewareMapper commonMiddlewareMapper;

	public TextStreamResponse onQuery() {
		// ajax.req(key, params);
		final CommonMiddleware commonMiddleware = TapestryAssist.getBeanFromPage(CommonMiddleware.class,
				requestGlobals);
		QueryWrapper<CommonMiddleware> queryWrapper = new QueryWrapper<CommonMiddleware>();
		if (StringUtil.isNotNull(commonMiddleware.getName())) {
			queryWrapper.likeRight("name", commonMiddleware.getName());
		}
		Page<CommonMiddleware> selectPage = commonMiddlewareMapper.selectPage(WebTools.buildPage(request),
				queryWrapper);
		String retstr = EasyUiAssist.getJsonForGridAlias(selectPage.getRecords(), selectPage.getTotal());
		return TapestryAssist.getTextStreamResponse(retstr);
	}

	public TextStreamResponse onSave() {
		final CommonMiddleware commonMiddleware = TapestryAssist.getBeanFromPage(CommonMiddleware.class,
				requestGlobals);

		if (commonMiddleware.getId() == null) {
			commonMiddlewareMapper.insert(commonMiddleware);
		} else {
			commonMiddlewareMapper.updateByPrimaryKeySelective(commonMiddleware);
		}
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDel() {
		String id = request.getParameter("id");
		commonMiddlewareMapper.deleteById(id);
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onQuerysubcombobox() {
		String jsonStr = "[]";
		if (!request.getParameterNames().contains("parent")) {
			// jsonStr = JSONUtil. getJsonForList(subList, "id", "parent", "text");
		} else {
			final String parentid = request.getParameter("parent");
			MiddlewareType middlewareType = MiddlewareType.valueOf(parentid);
			String[] verPlugins = middlewareType.getPluginVers();

			jsonStr = JSONUtil.getJsonForListSimple(Arrays.asList(verPlugins));
		}
		return TapestryAssist.getTextStreamResponse(jsonStr);
	}
}