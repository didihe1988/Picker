package com.didihe1988.picker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.didihe1988.picker.utils.HttpUtils;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in SessionInterceptor");
		String uri = request.getRequestURI();
		System.out.println(uri);
		if (!uri.endsWith("enter") && !uri.endsWith("login")) {
			if (!HttpUtils.isSessionUserIdExists(request)) {
				response.sendRedirect("/enter");
				return false;
			}
		}
		return true;
	}

}
