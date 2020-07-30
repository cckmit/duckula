package net.wicp.tams.duckula.plugin.http.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import net.wicp.tams.common.Conf;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.binlog.alone.BusiAssit;
import net.wicp.tams.common.binlog.dump.MainDump;

public class TestHttpPlugin {

	@Test
	public void startListenerForConfigFile() throws IOException {
		Properties props = IOUtil.fileToProperties("/localhost-binlog.properties", TestHttpPlugin.class);
		Conf.overProp(props);
		BusiAssit.startListenerForConfig();
		System.in.read();
	}

	@Test
	public void dump()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Properties props = IOUtil.fileToProperties("/localhost-dump.properties", TestHttpPlugin.class);
		Conf.overProp(props);
		MainDump main = new MainDump();
		main.dump(null);
		System.in.read();
	}

}
