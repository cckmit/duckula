package net.wicp.tams.duckula.ops.pages.setting;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.wicp.tams.app.duckula.controller.bean.models.CommonCheckpoint;
import net.wicp.tams.app.duckula.controller.dao.CommonCheckpointMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.WebTools;

public class CheckpointManager {
	@Inject
	protected RequestGlobals requestGlobals;

	@Inject
	protected Request request;

	@Inject
	private IReq req;

	@Inject
	private CommonCheckpointMapper commonCheckpointMapper;

	public TextStreamResponse onQuery() {
		// ajax.req(key, params);
		final CommonCheckpoint commonCheckpoint = TapestryAssist.getBeanFromPage(CommonCheckpoint.class, requestGlobals);
		QueryWrapper<CommonCheckpoint> queryWrapper = new QueryWrapper<CommonCheckpoint>();
		if (StringUtil.isNotNull(commonCheckpoint.getName())) {
			queryWrapper.likeRight("name", commonCheckpoint.getName());
		}
		Page<CommonCheckpoint> selectPage = commonCheckpointMapper.selectPage(WebTools.buildPage(request), queryWrapper);
		String retstr = EasyUiAssist.getJsonForGridAlias(selectPage.getRecords(), selectPage.getTotal());
		return TapestryAssist.getTextStreamResponse(retstr);
	}

	public TextStreamResponse onSave() {
		final CommonCheckpoint commonCheckpoint = TapestryAssist.getBeanFromPage(CommonCheckpoint.class, requestGlobals);
		if (commonCheckpoint.getId() == null) {
			commonCheckpointMapper.insert(commonCheckpoint);
		} else {
			commonCheckpointMapper.updateByPrimaryKeySelective(commonCheckpoint);
		}
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDel() {
		String id = request.getParameter("id");
		commonCheckpointMapper.deleteById(id);
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}
}