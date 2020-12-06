package net.wicp.tams.duckula.ops.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;

import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.constant.dic.YesOrNo;
import net.wicp.tams.duckula.ops.beans.SessionBean;

@Import(stack = "easyuistack")
public class Login {
	@Inject
	private Logger logger;

	@Inject
	protected Request request;

	@SessionState
	private SessionBean sessionBean;

	Object onSuccess() {
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		if (StringUtil.isNull(userName) || StringUtil.isNull(pwd)) {
			return Login.class;
		}
		if (!"admin".equals(userName) || !"admin123".equals(pwd)) {
			return Login.class;
		}
		sessionBean = new SessionBean();
		sessionBean.setIsLogin(YesOrNo.yes);
		return Index.class;
	}

}
