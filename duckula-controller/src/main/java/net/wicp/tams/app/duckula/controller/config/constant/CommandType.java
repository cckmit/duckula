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
