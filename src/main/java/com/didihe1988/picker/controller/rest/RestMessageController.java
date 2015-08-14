package com.didihe1988.picker.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestMessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/json/message/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getMessage(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		Message message = messageService.getMessageById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_MESSAGE,
				message);
	}
}
