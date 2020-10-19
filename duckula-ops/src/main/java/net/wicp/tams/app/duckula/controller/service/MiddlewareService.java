package net.wicp.tams.app.duckula.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddlewareExample;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddlewareExample.Criteria;
import net.wicp.tams.app.duckula.controller.config.constant.MiddlewareType;
import net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper;
import net.wicp.tams.app.duckula.controller.service.shiro.ShiroAssit;
import net.wicp.tams.common.apiext.StringUtil;

@Service
public class MiddlewareService {
	@Autowired
	private CommonMiddlewareMapper commonMiddlewareMapper;

	public List<CommonMiddleware> queryMiddleware(MiddlewareType middlewareType, String version) {
		CommonMiddlewareExample example = new CommonMiddlewareExample();
		Criteria criteria = example.createCriteria();
		criteria.andMiddlewareTypeEqualTo(middlewareType.name());
		if (StringUtil.isNotNull(version)) {
			criteria.andVersionEqualTo(version);
		}
		ShiroAssit.authority(criteria);
		List<CommonMiddleware> retlist = commonMiddlewareMapper.selectByExample(example);
		return retlist;
	}
}