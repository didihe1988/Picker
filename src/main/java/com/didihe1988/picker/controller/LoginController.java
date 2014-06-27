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
		/*boolean isValidUser = userService.hasMatchUser(username, password);
		
		if (!isValidUser) {
			return "/login";
		} else {
			User user = userService.findUserByUserName(username);
			user.setLastVisit(new Date());
			userService.updateUser(user);
			System.out.println(user.getPassword());
			setSessionUser(request, user);
			return "redirect:/book/list.do";
		}*/
		return "redirect:/register/addUser.do";
	}

	@RequestMapping(value = "/login/hibernate_test.do")
	public String test() {
		return "redirect:/register/addUser.do";
	}

}
