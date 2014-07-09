package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.User;

public class BaseController {
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Constant.USER);
	}

	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constant.USER, user);
	}

	protected void removeSessionUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.USER);
	}

	protected int getIntegerFromReqeust(HttpServletRequest request,
			String parameter) {
		return Integer.parseInt(request.getParameter(parameter));
	}
}
