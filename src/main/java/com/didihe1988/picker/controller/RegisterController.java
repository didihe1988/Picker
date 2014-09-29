package com.didihe1988.picker.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.form.RegisterForm;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RegisterController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute RegisterForm registerForm, HttpServletRequest request,HttpServletResponse response) {
		int status = Status.ERROR;
		if (userService.isEmailExists(registerForm.getEmail())) {
			//status = Status.EXISTS;
			//后期加上toast
			return "enter";
		} else {
			User user=new User(registerForm.getName(), registerForm.getEmail(), registerForm.getPassword(), "/resources/image/avatar/user_avatar2.jpg","tmp");
			/*
			 * 加密在UserService完成
			 */
			status = userService.addUser(user);
			if(status==Status.SUCCESS)
			{
				try {
					user=userService.getUserByEmail(registerForm.getEmail());
					response.sendRedirect("/picker/user/"+user.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
		return "enter";
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
