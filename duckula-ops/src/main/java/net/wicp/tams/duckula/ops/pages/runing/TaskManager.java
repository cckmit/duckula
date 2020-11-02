package net.wicp.tams.duckula.ops.pages.runing;

import java.util.Map;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.wicp.tams.app.duckula.controller.BusiTools;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.dao.CommonCheckpointMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonInstanceMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonVersionMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.CollectionUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.apiext.json.EasyUiAssist;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleManager;
import net.wicp.tams.common.callback.IConvertValue;
import net.wicp.tams.component.annotation.HtmlJs;
import net.wicp.tams.component.constant.EasyUIAdd;
import net.wicp.tams.component.services.IReq;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.WebTools;

@HtmlJs(easyuiadd = { EasyUIAdd.edatagrid })
public class TaskManager {
	@Inject
	protected RequestGlobals requestGlobals;

	@Inject
	protected Request request;

	@Inject
	private IReq req;

	@Inject
	private CommonTaskMapper commonTaskMapper;
	@Inject
	private CommonVersionMapper commonVersionMapper;
	@Inject
	private CommonDeployMapper commonDeployMapper;

	@Inject
	private CommonMiddlewareMapper commonMiddlewareMapper;
	@Inject
	private CommonInstanceMapper commonInstanceMapper;
	@Inject
	private CommonCheckpointMapper commonCheckpointMapper;

	public TextStreamResponse onQuery() {
		// ajax.req(key, params);
		final CommonTask commonCheckpoint = TapestryAssist.getBeanFromPage(CommonTask.class, requestGlobals);
		QueryWrapper<CommonTask> queryWrapper = new QueryWrapper<CommonTask>();
		if (StringUtil.isNotNull(commonCheckpoint.getName())) {
			queryWrapper.likeRight("name", commonCheckpoint.getName());
		}
		Page<CommonTask> selectPage = commonTaskMapper.selectPage(WebTools.buildPage(request), queryWrapper);
		IConvertValue<String> versionConvert = new IConvertValue<String>() {
			private Map<Integer, String> datamap = BusiTools.convertValues(selectPage.getRecords(), commonVersionMapper,
					"versionId", "mainVersion");

			@Override
			public String getStr(String keyObj) {
				return StringUtil.isNull(keyObj) ? "" : datamap.get(Integer.parseInt(keyObj));
			}
		};

		IConvertValue<String> deployConvert = new IConvertValue<String>() {
			private Map<Integer, String> datamap = BusiTools.convertValues(selectPage.getRecords(), commonDeployMapper,
					"deployId", "name", "deploy");

			@Override
			public String getStr(String keyObj) {
				return StringUtil.isNull(keyObj) ? "" : datamap.get(Integer.parseInt(keyObj));
			}
		};
		IConvertValue<String> middlewareConvert = new IConvertValue<String>() {
			private Map<Integer, String> datamap = BusiTools.convertValues(selectPage.getRecords(),
					commonMiddlewareMapper, "middlewareId", "name", "middlewareType");

			@Override
			public String getStr(String keyObj) {
				return StringUtil.isNull(keyObj) ? "" : datamap.get(Integer.parseInt(keyObj));
			}
		};

		IConvertValue<String> instanceConvert = new IConvertValue<String>() {
			private Map<Integer, String> datamap = BusiTools.convertValues(selectPage.getRecords(),
					commonInstanceMapper, "instanceId", "name", "host");

			@Override
			public String getStr(String keyObj) {
				return StringUtil.isNull(keyObj) ? "" : datamap.get(Integer.parseInt(keyObj));
			}
		};

		IConvertValue<String> checkpointConvert = new IConvertValue<String>() {
			private Map<Integer, String> datamap = BusiTools.convertValues(selectPage.getRecords(),
					commonCheckpointMapper, "checkpointId", "name");

			@Override
			public String getStr(String keyObj) {
				return StringUtil.isNull(keyObj) ? "" : datamap.get(Integer.parseInt(keyObj));
			}
		};

		String retstr = EasyUiAssist.getJsonForGridAlias2(selectPage.getRecords(),
				new String[] { "versionId,version1", "deployId,deployId1", "middlewareId,middlewareId1",
						"instanceId,instanceId1", "checkpointId,checkpoint1" },
				CollectionUtil.newMap("version1", versionConvert, "deployId1", deployConvert, "middlewareId1",
						middlewareConvert, "instanceId1", instanceConvert, "checkpoint1", checkpointConvert),
				selectPage.getTotal());
		return TapestryAssist.getTextStreamResponse(retstr);
	}

	public TextStreamResponse onSave() {
		final CommonTask commonCheckpoint = TapestryAssist.getBeanFromPage(CommonTask.class, requestGlobals);
		if (commonCheckpoint.getId() == null) {
			commonTaskMapper.insert(commonCheckpoint);
		} else {
			commonTaskMapper.updateByPrimaryKeySelective(commonCheckpoint);
		}
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDel() {
		String id = request.getParameter("id");
		commonTaskMapper.deleteById(id);
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDataConvert() {
		String saveDataStr = request.getParameter("saveData");
		JSONObject dgAll = JSONObject.parseObject(saveDataStr);
		com.alibaba.fastjson.JSONArray rows = dgAll.getJSONArray("rows");
		RuleManager ruleManager = new RuleManager(rows);
		return TapestryAssist.getTextStreamResponse(Result.getSuc(ruleManager.toString()));
	}

	public TextStreamResponse onRuleData() {
		String commandtypeStr = request.getParameter("ruleData");
		RuleManager ruleManager=new RuleManager(commandtypeStr);
		JSONArray retAry = ruleManager.toJsonAry();
		return TapestryAssist.getTextStreamResponse(retAry.toJSONString());
	}
}