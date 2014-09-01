package com.didihe1988.picker.utils;

import javax.servlet.http.HttpServletRequest;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.User;

public class HttpUtils {
	private HttpUtils() {

	}

	public static User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Constant.USER);
	}

	public static int getSessionUserId(HttpServletRequest request) {
		// System.out.println(request);
		// System.out.println(request.getSession());
		// System.out.println(request.toString());
		// request.getSession().
		return (Integer) request.getSession().getAttribute(Constant.USERID);
	}

	public static String getSessionUserName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Constant.USERNAME);
	}

	public static void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constant.USER, user);
	}

	public static void setSessionUserId(HttpServletRequest request, int userId) {
		request.getSession().setAttribute(Constant.USERID, userId);
	}

	public static void setSessionUserName(HttpServletRequest request,
			String userName) {
		request.getSession().setAttribute(Constant.USERNAME, userName);
	}

	public static void removeSessionUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.USER);
	}

	public static int getIntegerFromReqeust(HttpServletRequest request,
			String parameter) {
		return Integer.parseInt(request.getParameter(parameter));
	}

	public static String getStringFromReqeust(HttpServletRequest request,
			String parameter) {
		return (String) request.getParameter(parameter);
	}

}
