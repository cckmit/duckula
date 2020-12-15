package net.wicp.tams.duckula.ops.pages;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.app.duckula.controller.bean.models.SysGlobal;
import net.wicp.tams.app.duckula.controller.bean.models.SysGlobalExample;
import net.wicp.tams.app.duckula.controller.dao.SysGlobalMapper;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.constant.dic.YesOrNo;
import net.wicp.tams.component.assistbean.Menu;
import net.wicp.tams.component.constant.ResType;
import net.wicp.tams.component.tools.TapestryAssist;
import net.wicp.tams.duckula.ops.beans.SessionBean;

/**
 * Start page of application duckula-ops.
 */
public class Index {

	@Inject
	@Path("menu.properties")
	private Asset asset;

	@SessionState
	private SessionBean sessionBean;

	private boolean sessionBeanExists;

	@InjectPage
	private Login login;

	@Inject
	protected Request request;

	@Inject
	private SysGlobalMapper sysGlobalMapper;

	@OnEvent(value = "switchMenu")
	public List<Menu> switchMenu(String moudleId) throws IOException {
		Properties prop = new Properties();
		prop.load(asset.getResource().openStream());
		String[] menus = prop.getProperty("menu.all").split(",");
		List<Menu> retlist = CollectionFactory.newList();
		for (int i = 0; i < menus.length; i++) {
			if (!menus[i].startsWith(moudleId)) {
				continue;
			}
			String id = prop.getProperty(String.format("%s.id", menus[i]));

			String resName = prop.getProperty(String.format("%s.resName", menus[i]));
			String resType = prop.getProperty(String.format("%s.resType", menus[i]));
			String resValue = prop.getProperty(String.format("%s.resValue", menus[i]));
			if (StringUtil.isNotNull(id) && StringUtil.isNotNull(resName) && StringUtil.isNotNull(resType)
					&& StringUtil.isNotNull(resValue)) {
				Menu menu = Menu.builder().id(id).resName(resName).resType(ResType.get(resType)).resValue(resValue)
						.build();
				retlist.add(menu);
			}
		}
		return retlist;
	}

	public TextStreamResponse onDataSave() {
		String saveDataStr = request.getParameter("saveData");
		JSONObject dgAll = JSONObject.parseObject(saveDataStr);
		JSONArray rows = dgAll.getJSONArray("rows");
		System.out.println("rows=" + rows.size());
		// TODO 保存全局配置
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public TextStreamResponse onDataInit() {
		List<SysGlobal> allConfig = sysGlobalMapper.selectByExample(new SysGlobalExample());
		JSONArray rows = new JSONArray();
		if (CollectionUtils.isNotEmpty(allConfig)) {
			for (SysGlobal sysGlobal : allConfig) {
				JSONObject parseObject = new JSONObject();
				parseObject.put("name", sysGlobal.getName());
				parseObject.put("value", sysGlobal.getValue());
				parseObject.put("group", sysGlobal.getGroupName());
				if (StringUtil.isNotNull(sysGlobal.getValidType())) {
					parseObject.put("editor",
							JSON.parseObject(String.format("{\"type\":\"%s\",\"options\":{\"validType\":\"%s\"}}",
									sysGlobal.getType(), sysGlobal.getValidType())));
				} else {
					parseObject.put("editor", sysGlobal.getType());
				}
				rows.add(parseObject);
			}
		}
		JSONObject retjson = new JSONObject();
		retjson.put("rows", rows);
		retjson.put("total", retjson.size());
		return TapestryAssist.getTextStreamResponse(rows.toJSONString());
	}

	public TextStreamResponse onLogout() {
		sessionBean = null;
		return TapestryAssist.getTextStreamResponse(Result.getSuc());
	}

	public Object onActivate() {
		if (!sessionBeanExists || sessionBean == null || sessionBean.getIsLogin() == YesOrNo.no) {
			return login;
		} else {
			return null;
		}
	}
}
