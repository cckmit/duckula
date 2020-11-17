package net.wicp.tams.app.duckula.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.mvel2.templates.TemplateRuntime;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonCheckpoint;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDump;
import net.wicp.tams.app.duckula.controller.bean.models.CommonInstance;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.dao.CommonCheckpointMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonDumpMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonInstanceMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper;
import net.wicp.tams.common.apiext.CollectionUtil;
import net.wicp.tams.common.apiext.IOUtil;

@Slf4j
public abstract class BusiTools {
	public static String getVersion(String dirPath, String relaPath) {
		try {
			String context = FileUtils.readFileToString(new File(IOUtil.mergeFolderAndFilePath(dirPath, relaPath)));
			int indexOf = context.indexOf("\r\n");
			String version = context.substring(8, indexOf);
			return version;
		} catch (Exception e) {
			log.error("解析tar文件失败", e);
			return "last";
		}
	}

	public static <T1, T2> Map<Long, String> convertValues(List<T1> oriList, BaseMapper<T2> maper, String oriColName,
			String... colNames) {
		Map<Long, String> retmap = new HashMap<Long, String>();
		if(CollectionUtils.isEmpty(oriList)) {
			return retmap;
		}
		Set<String> ids = CollectionUtil.getColSetFromObj(oriList, oriColName);
		List<T2> retListObj = maper.selectBatchIds(ids);		
		String tempstr="@{id}~~~";
		
		for (int i = 0; i < colNames.length; i++) {
			if(i==0) {
				tempstr+="@{"+colNames[i]+"}";
			}else {
				tempstr+="【@{"+colNames[i]+"}】";
			}
		}
		
		for (T2 t2 : retListObj) {
			
			//@{'{"name":"'+name+'","id":"'+id+'",
		//	String jsonTempStr = "@['{label:\"'+" + nameFiled + "+'\",value:\"'+"
		//			+ codeFiled + "+'\",id:\"'+" + idName + "+'\"},']";
		
			String value = String.valueOf(TemplateRuntime.eval(tempstr, t2));
			String[] tempAry = value.split("~~~");
			retmap.put(Long.parseLong(tempAry[0]), tempAry[1]);
		}
		return retmap;
	}

	public static String configContext(CommonTaskMapper commonTaskMapper,CommonCheckpointMapper commonCheckpointMapper,CommonDumpMapper commonDumpMapper,CommonMiddlewareMapper commonMiddlewareMapper,CommonInstanceMapper commonInstanceMapper,  CommandType commandType, Long taskId, Map<String, Object> params) {
		Long middlewareId = null;
		String configName = null;
		Long instanceId = null;
		switch (commandType) {
		case task:
			CommonTask selectTask = commonTaskMapper.selectById(taskId);
			CommonCheckpoint commonCheckpoint = commonCheckpointMapper.selectById(selectTask.getCheckpointId());
			params.putAll(CommandType.proTaskConfig(selectTask, commonCheckpoint));// 默认配置
			configName = commandType.formateConfigName(selectTask.getName());
			middlewareId = selectTask.getMiddlewareId();
			instanceId = selectTask.getInstanceId();
			break;
		case dump:
			CommonDump commonDump = commonDumpMapper.selectById(taskId);
			params.putAll(CommandType.proDumpConfig(commonDump));// 默认配置
			configName = commandType.formateConfigName(commonDump.getName());
			middlewareId = commonDump.getMiddlewareId();
			instanceId = commonDump.getInstanceId();
			break;	
		default:
			break;
		}
		CommonMiddleware middleware = commonMiddlewareMapper.selectById(middlewareId);
		MiddlewareType middlewareType = MiddlewareType.valueOf(middleware.getMiddlewareType());
		// 配置插件
		Map<String, Object> pluginConfig = middlewareType.proPluginConfig(commandType, middleware.getVersion());
		params.putAll(pluginConfig);
		// 配置目标中间件
		Map<String, Object> proConfig = middlewareType.proConfig(middleware);
		params.putAll(proConfig);
		// 配置监听实例,如consumer可能就没有这个实例
		if (instanceId != null) {
			CommonInstance commonInstance = commonInstanceMapper.selectById(instanceId);
			params.putAll(configInstall(commandType,commonInstance));
		}
		return configName;
	}
	
	// 配置监听实例
	public static  Map<String, Object> configInstall(CommandType taskType, CommonInstance commonInstance) {
		Map<String, Object> tempmap = new HashMap<String, Object>();
		switch (taskType) {
		case task:
			tempmap.put("common.binlog.alone.binlog.global.conf.host", commonInstance.getHost());
			tempmap.put("common.binlog.alone.binlog.global.conf.port", commonInstance.getPort());
			tempmap.put("common.binlog.alone.binlog.global.conf.username", commonInstance.getUsername());
			tempmap.put("common.binlog.alone.binlog.global.conf.password", commonInstance.getPassword());
			tempmap.put("common.binlog.alone.binlog.global.conf.rds", "false");// 写死为false,不理rds
			break;
		case dump:
			tempmap.put("common.binlog.alone.dump.global.pool.host", commonInstance.getHost());
			tempmap.put("common.binlog.alone.dump.global.pool.port", commonInstance.getPort());
			tempmap.put("common.binlog.alone.dump.global.pool.username", commonInstance.getUsername());
			tempmap.put("common.binlog.alone.dump.global.pool.password", commonInstance.getPassword());
			// tempmap.put("common.binlog.alone.binlog.global.conf.rds", "false");//
			// 写死为false,不理rds dump不认
			break;
		default:
			break;
		}

		return tempmap;
	}
}
