package com.didihe1988.picker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;

@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	
}
