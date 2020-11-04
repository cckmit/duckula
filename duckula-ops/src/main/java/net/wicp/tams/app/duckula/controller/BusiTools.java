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
}
