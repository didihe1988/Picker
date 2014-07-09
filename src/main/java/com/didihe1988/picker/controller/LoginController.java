package com.didihe1988.picker.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.model.LoginForm;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/*
	 * String loginCheck(HttpServletRequest request,
	 * 
	 * @ModelAttribute("loginForm") LoginForm loginForm)
	 */
	@RequestMapping(value = "/login/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + ":" + password);

		boolean isValid = userService.hasMatchUser(username, password);

		if (!isValid) {
			return "login";
		} else {
			User user = userService.getUserByUserName(username);
			user.setLastVisit(new Date());
			userService.updateUser(user);
			HttpUtils.setSessionUser(request, user);
			return "redirect:/book/list.do";
		}

		// return "redirect:/register/addUser.do";
	}
}
