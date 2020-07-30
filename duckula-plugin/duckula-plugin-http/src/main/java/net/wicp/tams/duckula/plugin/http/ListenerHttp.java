package net.wicp.tams.duckula.plugin.http;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.binlog.alone.ListenerConf.ColHis;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEvent;
import net.wicp.tams.common.binlog.alone.ListenerConf.DuckulaEventItem;
import net.wicp.tams.common.binlog.alone.ListenerConf.OptType;
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
	public Result doAlterTableCallBack(ColHis colHis) {
		return Result.getSuc();// 不关心表结构的变化
	}

	@Override
	public void doBusiTrue(Rule rule, DuckulaEvent duckulaEvent) {
		JSONArray params = new JSONArray();
		for (DuckulaEventItem duckulaEventItem : duckulaEvent.getItemsList()) {
			Map<String, String> data = duckulaEvent.getOptType() == OptType.delete ? duckulaEventItem.getBeforeMap()
					: duckulaEventItem.getAfterMap();
			params.add(data);
		}
		HttpPluginAssit.sendMsg(HttpClient.packurl(rule.getItems().get(RuleItem.httpRela)), params);
	}

}
