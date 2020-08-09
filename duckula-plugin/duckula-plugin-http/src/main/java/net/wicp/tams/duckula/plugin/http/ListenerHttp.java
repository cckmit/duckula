package net.wicp.tams.duckula.plugin.http;

import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.binlog.alone.DuckulaAssit;
import net.wicp.tams.common.binlog.alone.ListenerConf.ColHis;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEvent;
import net.wicp.tams.common.binlog.alone.binlog.bean.Rule;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleItem;
import net.wicp.tams.common.binlog.alone.binlog.listener.AbsBinlogListener;
import net.wicp.tams.common.http.HttpClient;

public class ListenerHttp extends AbsBinlogListener {
	static {
		// 防止自定义classload的时错过默认的加载配置,再次加载配置文件
		Conf.overConf("/duckula-plugin-http.properties", ListenerHttp.class, false);
	}

	@Override
	public void doBusiTrue(Rule rule, DuckulaEvent duckulaEvent) {
		JSONObject data = DuckulaAssit.convertJson(duckulaEvent);
		HttpPluginAssit.sendMsg(HttpClient.packurl(rule.getItems().get(RuleItem.httpRela)), data);
	}

	@Override
	public Result doAlterTableCallBack(Rule rule, ColHis colHis) {
		return Result.getSuc();// 不关心表结构的变化
	}

	@Override
	public void doInit(Rule rule, int index) {

	}

}
