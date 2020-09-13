package net.wicp.tams.app.duckula.controller.config.constant;

import java.util.HashMap;
import java.util.Map;

import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.common.apiext.CollectionUtil;

/***
 * 3种运行模式
 * 
 * @author Andy.zhou
 *
 */
public enum CommandType {
	task(new String[] {}, "t-%s"),

	dump(new String[] {}, "d-%s");

	// 默认配置
	public final Map<String, Object> defaultconfig;

	private final String nameFormate;

	public Map<String, Object> getDefaultconfig() {
		return this.defaultconfig;
	}

	// task配置专用
	public static Map<String, Object> proTaskConfig(CommonTask commonTask) {
		Map<String, Object> retmap = new HashMap<String, Object>();
		retmap.putAll(CommandType.task.getDefaultconfig());

		retmap.put("common.binlog.alone.binlog.global.bufferType", commonTask.getBufferType());
		retmap.put("common.binlog.alone.binlog.global." + commonTask.getBufferType() + ".sendNum",
				commonTask.getSendnum());
		retmap.put("common.binlog.alone.binlog.global.chk", commonTask.getCheckpoint());
		if ("mysql".equals(commonTask.getCheckpoint())) {// TODO 加上mysql的配置

		}
		// TODO 业务监听器
		retmap.put("common.binlog.alone.binlog.global.conf.groupId", commonTask.getGroupId());
		retmap.put("common.binlog.alone.binlog.global.conf.clientId", commonTask.getClientId());
		retmap.put("common.binlog.alone.binlog.global.conf.haType", commonTask.getHaType());
		retmap.put("common.binlog.alone.binlog.global.conf.pos.gtids", commonTask.getGtids());
		return retmap;
	}

	@SuppressWarnings("unchecked")
	private CommandType(String[] configs, String nameFormate) {
		this.defaultconfig = CollectionUtil.newMap(configs);
		this.nameFormate = nameFormate;
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
}
