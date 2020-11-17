package net.wicp.tams.duckula.dump;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.constant.JvmStatus;

@Slf4j
public class MainDump {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		String rootDir = System.getenv("DUCKULA3_DATA");
		String relaPath="/conf/configmap.properties";
		if(ArrayUtils.isNotEmpty(args)) {
			relaPath=String.format("/conf/%s.properties", args[0]);
		}
		String configpath = IOUtil.mergeFolderAndFilePath(rootDir, relaPath);
		log.info("使用配置文件:{}",configpath);
		Properties props = IOUtil
				.fileToProperties(new File(configpath));
		if(props==null||props.isEmpty()) {
			log.error("没有正确的配置文件");
			LoggerUtil.exit(JvmStatus.s15);// 关机
		}
		Conf.overProp(props);
		net.wicp.tams.common.binlog.dump.MainDump main = new net.wicp.tams.common.binlog.dump.MainDump();
		main.dump(null);
		System.in.read();
	}
}
