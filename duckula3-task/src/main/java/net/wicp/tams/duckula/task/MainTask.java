package net.wicp.tams.duckula.task;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.apiext.LoggerUtil;
import net.wicp.tams.common.binlog.alone.BinlogStart;
import net.wicp.tams.common.binlog.alone.BusiAssit;
import net.wicp.tams.common.binlog.alone.ListenerConf.ConnConf;
import net.wicp.tams.common.constant.JvmStatus;

@Slf4j
public class MainTask {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		String rootDir = System.getenv("DUCKULA3_DATA");
		Properties props = IOUtil
				.fileToProperties(new File(IOUtil.mergeFolderAndFilePath(rootDir, "/conf/configmap.properties")));
		Conf.overProp(props);
		List<ConnConf.Builder> confs = BusiAssit.ConfigInit();
		if (CollectionUtils.isEmpty(confs) || confs.size() > 1) {
			log.error("一个任务只能监听一个实例");
		}
		try {
			BinlogStart.listening(confs.get(0));
		} catch (Throwable e) {
			LoggerUtil.exit(JvmStatus.s15);// 关机
		}
	}

}
