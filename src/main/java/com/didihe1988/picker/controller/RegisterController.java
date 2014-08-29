package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RegisterController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public String register(@RequestBody User user, HttpServletRequest request) {
		int status = Status.ERROR;
		if (userService.isUserExists(user)) {
			status = Status.EXISTS;
		} else {
			status = userService.addUser(user);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/register/check", method = RequestMethod.GET, headers = "Accept=application/json")
	public String checkRegister(HttpServletRequest request) {
		String status = Status.SUCCESS_STRING;
		String email = HttpUtils.getStringFromReqeust(request, "email");
		if (email != null) {
			if (userService.isEmailExists(email)) {
				status = Status.EMAIL_USED_STRING;
			}
		} else {
			String username = HttpUtils.getStringFromReqeust(request, "name");
			if (username != null) {
				if (userService.isUsernameExists(username)) {
					status = Status.USERNAME_USED_STRING;
				}
			}
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

}
