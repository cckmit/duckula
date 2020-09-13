package net.wicp.tams.app.duckula.controller.config.constant;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.common.apiext.CollectionUtil;
import net.wicp.tams.common.apiext.json.JSONUtil;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleItem;

public enum MiddlewareType {
	es("es搜索", "common.es.",
			new String[][] {
					{ "5.X", "net.wicp.tams.common.es.plugin.ListenerEs5", "net.wicp.tams.common.es.plugin.DumperEs5" },
					{ "6.X", "net.wicp.tams.common.es.plugin.ListenerEs6", "net.wicp.tams.common.es.plugin.DumperEs6" },
					{ "7.X", "net.wicp.tams.common.es.plugin.ListenerEs7",
							"net.wicp.tams.common.es.plugin.DumperEs7" } },
			new RuleItem[] { RuleItem.index, RuleItem.type, RuleItem.relakey, RuleItem.copynum, RuleItem.partitions }),

	manticore("manticore搜索", "", new String[][] { { "3.5" } }, new RuleItem[] {}),

	cassandra("cassandra数据库", "", new String[][] { { "3" } }, new RuleItem[] { RuleItem.ks, RuleItem.table }),

	mysql("mysql数据库", "", new String[][] { { "5.6" }, { "5.7" }, { "8.0" } }, new RuleItem[] { RuleItem.dbtb }),

	kafka("kafka消息", "", new String[][] { { "1.X" }, { "2.X" } }, new RuleItem[] { RuleItem.topic }),

	http("http服务器", "", new String[][] { { "1.1" } }, new RuleItem[] { RuleItem.httpRela }),

	;

	private final String desc;

	private final String[][] verPlugins;// 支持的版本,元素：0:版本 1：监听 2：全量

	private final RuleItem[] ruleItems;// 可以配置的item

	private final String pre;// 配置项目的前缀，

	private final RuleItem[] commonItems = new RuleItem[] { RuleItem.colName, RuleItem.addProp, RuleItem.splitkey };// 公共配置

	public Map<String, Object> proConfig(CommonMiddleware commonMiddleware) {
		Map<String, Object> retmap = new HashMap<String, Object>();
		switch (this) {
		case es:
			JSONObject opt = JSON.parseObject(commonMiddleware.getOpt());
			retmap.put("common.es.host.name", commonMiddleware.getHost());
			retmap.put("common.es.host.port.rest", String.valueOf(commonMiddleware.getPort()));
			retmap.put("common.es.host.port.transport", String.valueOf(commonMiddleware.getPort2()));
			Map<String, Object> map = JSONUtil.jsonToMap(opt, this.pre);
			retmap.putAll(map);
			// retmap.put("common.es.cluster.name",
			// opt.getString(ConfigItem.middleware_es_cluster_name));//
			// common.es.cluster.name
			break;
		case mysql:
			retmap.put(String.format("common.jdbc.datasource.%s.host", commonMiddleware.getId()),
					commonMiddleware.getHost());
			retmap.put(String.format("common.jdbc.datasource.%s.port", commonMiddleware.getId()),
					String.valueOf(commonMiddleware.getPort()));
		default:
			break;
		}
		return retmap;
	}

	public RuleItem[] getRuleItems() {
		return ruleItems;
	}

	public String[][] getVerPlugins() {
		return verPlugins;
	}

	public String getPre() {
		return pre;
	}

	public String[] getVerPluginByVersion(String version) {
		for (String[] verPlugin : verPlugins) {
			if (version.equalsIgnoreCase(verPlugin[0])) {
				return verPlugin;
			}
		}
		return null;
	}

	public String getDesc() {
		return desc;
	}

	private MiddlewareType(String desc, String pre, String[][] verPlugins, RuleItem[] ruleItems) {
		this.desc = desc;
		this.pre = pre;
		this.verPlugins = verPlugins;
		this.ruleItems = CollectionUtil.arrayMerge(RuleItem[].class, ruleItems, commonItems);
	}
}
