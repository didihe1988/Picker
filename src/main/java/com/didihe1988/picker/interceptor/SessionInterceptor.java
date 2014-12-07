package com.didihe1988.picker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.utils.HttpUtils;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("In SessionInterceptor");
		String uri = request.getRequestURI();
		if (!(uri.endsWith("enter") || uri.endsWith("login") || uri
				.endsWith("register"))) {
			if (!HttpUtils.isSessionUserIdExists(request)) {
				// 来自Android的请求不进行重定向
				/*
				 * if (!isAndroidRequest(request)) {
				 * response.sendRedirect("/enter"); }
				 */
				response.sendRedirect("/enter");
				return false;
			}
		}
		return true;
	}

	private boolean isAndroidRequest(HttpServletRequest request) {
		String platform = (String) request.getAttribute("platform");
		if (platform != null) {
			if (platform.equals(Constant.PLATFORM_ANDROID)) {
				return true;
			}
		}
		return false;
	}

}
