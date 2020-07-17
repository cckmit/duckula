package net.wicp.tams.duckula.dump;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;

public class MainDump {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		String rootDir = System.getenv("DUCKULA3_DATA");
		Properties props = IOUtil
				.fileToProperties(new File(IOUtil.mergeFolderAndFilePath(rootDir, "/conf/configmap.properties")));
		Conf.overProp(props);
		net.wicp.tams.common.binlog.dump.MainDump main = new net.wicp.tams.common.binlog.dump.MainDump();
		main.dump(null);
		System.in.read();
	}
}
