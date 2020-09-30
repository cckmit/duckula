package net.wicp.tams.app.duckula.controller.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import net.wicp.tams.common.apiext.StringUtil;

/***
 * 用于配置主机的hosts
 * 
 * @author andy
 *
 */
@Data
public class Host {
	private String ip;
	private String[] hostnames;

	/****
	 * eg:[{'ip':'192.168.123.23','hostnames':'kafka01,kafka001'}]
	 * 
	 * @param jsonAryStr
	 * @return
	 */
	public static List<Host> jsonToHosts(String jsonAryStr) {
		List<Host> retlist = new ArrayList<Host>();
		if (StringUtil.isNull(jsonAryStr)) {
			return retlist;
		}
		JSONArray parseArray = JSONArray.parseArray(jsonAryStr);
		for (int i = 0; i < parseArray.size(); i++) {
			JSONObject json = parseArray.getJSONObject(i);
			Host temp = new Host();
			temp.setIp(json.getString("ip"));
			String[] hostnames = json.getString("hostnames").split(",");
			temp.setHostnames(hostnames);
			retlist.add(temp);
		}
		return retlist;
	}
}
