package net.wicp.tams.app.duckula.controller.config.constant;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wicp.tams.app.duckula.controller.bean.models.CommonCheckpoint;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.common.apiext.CollectionUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.binlog.alone.binlog.bean.Rule;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleItem;
import net.wicp.tams.common.binlog.alone.binlog.bean.RuleManager;
import net.wicp.tams.common.constant.dic.intf.IEnumCombobox;

/***
 * 3种运行模式
 * 
 * @author Andy.zhou
 *
 */
public enum CommandType implements IEnumCombobox {
	task("监听任务", new String[] {}, "t-%s"),

	dump("全量导入", new String[] {}, "d-%s");

	// 默认配置
	public final Map<String, Object> defaultconfig;

	private final String nameFormate;

	public Map<String, Object> getDefaultconfig() {
		return this.defaultconfig;
	}

	// task配置专用
	public static Map<String, Object> proTaskConfig(CommonTask commonTask,CommonCheckpoint commonCheckpoint) {
		Map<String, Object> retmap = new HashMap<String, Object>();
		retmap.putAll(CommandType.task.getDefaultconfig());

		retmap.put("common.binlog.alone.binlog.global.bufferType", commonTask.getBufferType());
		retmap.put("common.binlog.alone.binlog.global." + commonTask.getBufferType() + ".sendNum",
				commonTask.getSendNum());
		retmap.put("common.binlog.alone.binlog.global.chk",commonCheckpoint.getCheckpointType());

		if ("mysql".equals(commonCheckpoint.getCheckpointType())) {// TODO 加上mysql的配置

		}
		// TODO 业务监听器
		retmap.put("common.binlog.alone.binlog.global.conf.groupId", commonTask.getGroupId());
		retmap.put("common.binlog.alone.binlog.global.conf.clientId", commonTask.getClientId());
		retmap.put("common.binlog.alone.binlog.global.conf.haType", commonTask.getHaType());
		retmap.put("common.binlog.alone.binlog.global.conf.pos.gtids", commonTask.getGtids());

		RuleManager ruleManager = new RuleManager(commonTask.getRule());
		for (Rule rule : ruleManager.getRules()) {
			// 自定义规则，如果是mysql,则需把dbinstanceid指向中间件ID，而中间件会做编码，把common.binlog.alone.plugin.jdbc.${MiddlewareId}.targetmysql.host做好编码
			// 配置了dbtb，表示是mysql目标中间件， 见MiddlewareType.proConfig()
			if (rule.getItems().containsKey(RuleItem.dbtb)) {
				rule.getItems().put(RuleItem.dbinstanceid, String.valueOf(commonTask.getMiddlewareId()));
			}
		}
		// 规则，使用全局的监听器
		retmap.put("common.binlog.alone.binlog.conf._global.rule", ruleManager.toString());

		// 其它的配置,如auto.create.index
		if (StringUtil.isNotNull(commonTask.getAttrConfig())) {
			JSONObject attrConfig = JSON.parseObject(commonTask.getAttrConfig());
			for (String key : attrConfig.keySet()) {
				retmap.put(key, attrConfig.getString(key));
			}
		}
		return retmap;
	}

	@SuppressWarnings("unchecked")
	private CommandType(String desc, String[] configs, String nameFormate) {
		this.defaultconfig = CollectionUtil.newMap(configs);
		this.nameFormate = nameFormate;
		this.desc = desc;
	}

	// 62是因为configmap有前缀有2位，而task前缀只有1位，这样可以取到相同的原taskname.
	public String formateTaskName(String taskName) {
		String idstr = String.format(this.nameFormate, taskName.replace("_", "-"));
		return idstr.length() >= 62 ? idstr.substring(0, 62) : idstr;
	}

	public String formateConfigName(String taskName) {
		String idstr = formateTaskName(taskName);
		return "c" + idstr;
	}

	private final String desc;

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return this.name();
	}

	@Override
	public String getDesc_zh() {
		return this.desc;
	}

	@Override
	public String getDesc_en() {
		return this.name();
	}
}
