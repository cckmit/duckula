package net.wicp.tams.app.duckula.controller.config.constant;

import java.util.Map;

import net.wicp.tams.common.apiext.CollectionUtil;

/***
 * 3种运行模式
 * 
 * @author Andy.zhou
 *
 */
public enum TaskType {
	task(new String[] {}),

	dump(new String[] {});

	//默认配置
	public final Map<String, String> defaultconfig;

	public Map<String, String> getDefaultconfig() {
		return defaultconfig;
	}

	private TaskType(String[] configs) {
		this.defaultconfig = CollectionUtil.newMap(configs);
	}
}
