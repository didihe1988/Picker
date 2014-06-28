package com.didihe1988.picker.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;

@Controller

public class RegisterController extends BaseController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register/addUser.do")
	public void addUser() {
		User user = new User();
		user.setLastVisit(new Date());
		user.setUsername("didihe1988");
		user.setPassword("mini2440");
		userService.addUser(user);
	}
}
