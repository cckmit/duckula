package net.wicp.tams.duckula.ops.pages.tools;

import org.apache.tapestry5.annotations.BeforeRenderTemplate;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;

import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.component.services.IReq;

public class ViewPodLog {

	@Inject
	protected RequestGlobals requestGlobals;
	@Inject
	protected Request request;
	@Inject
	private IReq req;

	@Property
	private String wsUri;
	@Property
	private String params;

	@BeforeRenderTemplate
	public void init() {
		// configName deployid
		String context = Conf.get("common.http.url.context");
		this.wsUri = context.replace(requestGlobals.getHTTPServletRequest().getScheme(), "ws");
		this.wsUri = IOUtil.mergeFolderAndFilePath(this.wsUri, "/websocket");
		String params = request.getParameter("params");
		String[] paramsAry = params.split(":");
		JSONObject json = new JSONObject();
		//commandType taskId  deployId
		json.put("commandType", paramsAry[0]);
		json.put("taskId", paramsAry[1]);
		json.put("deployId", paramsAry[2]);
		this.params = json.toJSONString();
	}

}
