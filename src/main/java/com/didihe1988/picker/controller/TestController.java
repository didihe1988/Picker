package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.utils.HttpUtils;

@RestController
public class TestController {
	@RequestMapping("/json/session")
	public String session(HttpServletRequest request)
	{
		int userId=HttpUtils.getSessionUserId(request);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userId",userId);
		return jsonObject.toString();
	}
}
