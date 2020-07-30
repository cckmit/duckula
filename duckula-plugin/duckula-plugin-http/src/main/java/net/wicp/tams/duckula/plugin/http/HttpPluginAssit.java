package net.wicp.tams.duckula.plugin.http;

import com.alibaba.fastjson.JSONArray;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.apiext.TimeAssist;
import net.wicp.tams.common.constant.JvmStatus;
import net.wicp.tams.common.exception.ExceptAll;
import net.wicp.tams.common.exception.ProjectException;
import net.wicp.tams.common.http.HttpClient;
import net.wicp.tams.common.http.HttpResult;

@Slf4j
public abstract class HttpPluginAssit {
	public static void sendMsg(String url, JSONArray array) {
		while (true) {
			try {
				HttpResult ret = HttpClient.doPostCommon(url, array.toJSONString()).call();

				log.info("return:{}", ret.getBodyStr());
				if (ret.getResult().isSuc()) {
					// break;
					throw new ProjectException(ExceptAll.Project_default, ret.getBodyStr());// 失败重试
				} else {
					throw new ProjectException(ExceptAll.Project_default, ret.getBodyStr());// 失败重试
				}
			} catch (Throwable e) {
				Integer reNum = Conf.getInt("duckula.plugin.http.retry");
				if (reNum < 0) {// 无限次重试
					continue;
				} else if (reNum == 0) {// 不重试
					break;
				} else {
					boolean reDoWait = TimeAssist.reDoWait("duckula-plugin-http", reNum);
					if (reDoWait) {// 达到最大值就出
						log.error("重试" + reNum + "次都不能拿到链接，退出");
						LoggerUtil.exit(JvmStatus.s15);
					} else {
						log.error("不能发送数据，重试", e);
						continue;
					}
				}
			}
		}
	}
}
