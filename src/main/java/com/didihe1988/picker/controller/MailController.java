package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.display.PrivateMessageSum;
import com.didihe1988.picker.service.PrivateMessageService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class MailController {
	@Autowired
	private PrivateMessageService PMService;

	@RequestMapping(value = "/mail")
	public String message(Model model, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		List<PrivateMessageSum> list = PMService
				.getPrivateMessageSumByUserId(userId);
		model.addAttribute("mailList", list);
		return "mail";
	}
}
