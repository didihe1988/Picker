package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;

public class MessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/message")
	public String message(Model model, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		model.addAttribute("messageList", messageService
				.getMessageDpByReceiverIdAndFilter(userId,
						Message.Filter.MESSAGE_RELATED));
		return "message";
	}

}
