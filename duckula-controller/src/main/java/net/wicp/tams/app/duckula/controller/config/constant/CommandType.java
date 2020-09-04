package net.wicp.tams.app.duckula.controller.config.constant;

import java.util.Map;

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
	public final Map<String, String> defaultconfig;

	private final String nameFormate;

	public Map<String, String> getDefaultconfig() {
		return defaultconfig;
	}

	@SuppressWarnings("unchecked")
	private CommandType(String[] configs, String nameFormate) {
		this.defaultconfig = CollectionUtil.newMap(configs);
		this.nameFormate = nameFormate;
	}

	public String formateTaskName(String taskName) {
		String idstr = taskName.replace("_", "-");
		if (idstr.length() >= 63) {// TODO 具63再定
			return String.format(this.nameFormate, idstr.substring(0, 63));
		} else {
			return String.format(this.nameFormate, idstr);
		}
	}
}
