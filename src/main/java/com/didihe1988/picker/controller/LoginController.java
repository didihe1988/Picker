package com.didihe1988.picker.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.model.LoginForm;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;

@Controller
public class LoginController extends BaseController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request,
			@ModelAttribute("loginForm") LoginForm loginForm) {
		boolean isValidUser = userService.hasMatchUser(loginForm.getUsername(),
				loginForm.getPassword());
		if (!isValidUser) {
			return "/login";
		} else {
			User user = userService.findUserByUserName(loginForm.getUsername());
			user.setLastVisit(new Date());
			userService.updateUser(user);
			setSessionUser(request, user);
			return "/book/list";
		}
	}

}
