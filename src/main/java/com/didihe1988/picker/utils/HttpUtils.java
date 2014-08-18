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
		return (Integer) request.getSession().getAttribute(Constant.USERID);
	}

	public static void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constant.USER, user);
	}

	public static void setSessionUserId(HttpServletRequest request, int userId) {
		request.getSession().setAttribute(Constant.USERID, userId);
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

	// É¾³ý·´Ð±Ïß
	public static String deleteBackslash(String rawString) {
		int length = rawString.length();
		Character backslash = new Character('\\');
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			Character character = new Character(rawString.charAt(i));
			// returns the value 0 if the argument Character is equal to this
			// Character
			if (character.compareTo(backslash) != 0) {
				stringBuffer.append(character);
			}
		}
		return stringBuffer.toString();
	}
}
