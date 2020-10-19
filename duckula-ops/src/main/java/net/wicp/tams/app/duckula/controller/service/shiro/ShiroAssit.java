package net.wicp.tams.app.duckula.controller.service.shiro;

import java.util.List;
import java.util.function.Predicate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import net.wicp.tams.app.duckula.controller.bean.models.SysPermissions;
import net.wicp.tams.app.duckula.controller.bean.models.SysRole;
import net.wicp.tams.app.duckula.controller.bean.models.SysUser;
import net.wicp.tams.common.apiext.ReflectAssist;

public abstract class ShiroAssit {
	public static String UserAttribute = "attribute_user";

	public static SysUser curUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getSession().getAttribute(UserAttribute);
		return user;
	}

	// 添加权限
	public static void authority(Object criteria) {
		if (!ShiroAssit.isAdmin()) {
			ReflectAssist.invokeMothed(criteria, "andUserIdEqualTo", curUser().getUsername());
		}
	}

	public static boolean isAdmin() {
		List<SysRole> roleList = curUser().getRoleList();
		return roleList.stream().anyMatch(new Predicate<SysRole>() {
			@Override
			public boolean test(SysRole t) {
				return t.getSysPermissionsList().stream().anyMatch(new Predicate<SysPermissions>() {
					@Override
					public boolean test(SysPermissions t) {
						return "admin".equals(t.getName());
					}
				});
			}
		});
	}

}
