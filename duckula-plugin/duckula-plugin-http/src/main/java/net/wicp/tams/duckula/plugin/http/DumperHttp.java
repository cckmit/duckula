package net.wicp.tams.duckula.plugin.http;

import org.apache.commons.lang3.Validate;

import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.binlog.alone.DuckulaAssit;
import net.wicp.tams.common.binlog.alone.dump.bean.Dump;
import net.wicp.tams.common.binlog.alone.dump.bean.DumpEvent;
import net.wicp.tams.common.binlog.alone.dump.listener.IBusiSender;
import net.wicp.tams.common.http.HttpClient;

/***
 * 需要把界面的dump配置转成Dump对象
 * 
 * @author andy.zhou
 *
 */
public class DumperHttp implements IBusiSender<DumpEvent> {
	static {
		// 防止自定义classload的时错过默认的加载配置,再次加载配置文件
		Conf.overConf("/duckula-plugin-http.properties", ListenerHttp.class, false);
	}

	private String httpRela;

	@Override
	public void init(Dump dump) {
		Validate.notEmpty(dump.getBusiPluginConfig(), "需要扩展属性，它是一个json且包含有 httpRela值。");
		Validate.notBlank(dump.getBusiPluginConfig().getString("httpRela"), "需要扩展属性，它是一个json且包含有 httpRela值。");
		this.httpRela = dump.getBusiPluginConfig().getString("httpRela");
	}

	@Override
	public void initParams(JSONObject params) {
	}

	@Override
	public void doSend(DumpEvent dumpEvent) {
		JSONObject data = DuckulaAssit.convertJson(dumpEvent);
		HttpPluginAssit.sendMsg(HttpClient.packurl(this.httpRela), data);
	}

}
