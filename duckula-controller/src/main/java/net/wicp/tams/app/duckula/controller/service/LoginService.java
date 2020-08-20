package net.wicp.tams.app.duckula.controller.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wicp.tams.app.duckula.controller.bean.models.SysPermissions;
import net.wicp.tams.app.duckula.controller.bean.models.SysRole;
import net.wicp.tams.app.duckula.controller.bean.models.SysUser;
import net.wicp.tams.app.duckula.controller.dao.SysPermissionsMapper;
import net.wicp.tams.app.duckula.controller.dao.SysRoleMapper;
import net.wicp.tams.app.duckula.controller.dao.SysUserMapper;

@Service
public class LoginService {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysPermissionsMapper sysPermissionsMapper;

	public SysUser getUserByName(String username) {
		SysUser sysUser = sysUserMapper.selectById(1l);
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(1l);
		SysPermissions sysPermissions = sysPermissionsMapper.selectByPrimaryKey(1l);
		sysRole.setSysPermissionsList(Arrays.asList(new SysPermissions[] { sysPermissions }));
		sysUser.setRoleList(Arrays.asList(new SysRole[] { sysRole }));
		return sysUser;
	}

}
