package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;

@RestController
public class RegisterController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register/addUser.do", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// User user = new User(username, password);
		// userService.addUser(user);
		// 测试通过 没有写返回界面 直接返回到了addUser.jsp
	}
}
