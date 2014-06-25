package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.User;

public class BaseController {
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Constant.USER_CONTEXT);
	}

	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constant.USER_CONTEXT, user);
	}

	protected void removeSessionUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.USER_CONTEXT);
	}
}
