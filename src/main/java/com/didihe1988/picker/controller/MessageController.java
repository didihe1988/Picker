package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "message/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int receiverId = HttpUtils.getIntegerFromReqeust(request, "receiverId");
		List<Message> messageList = messageService
				.getMessageByReceiverId(receiverId);
		System.out.println(messageList);
		modelMap.addAttribute("messageList", messageList);
		return "messagelist";
	}

	@RequestMapping(value = "message/setchecked.do")
	public String setChecked(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		messageService.setMessageChecked(id);
		return "messagelist";
	}

	@RequestMapping(value = "message/add.do")
	public String addMessage(HttpServletRequest request) {
		int type = HttpUtils.getIntegerFromReqeust(request, "type");
		int sourceId = HttpUtils.getIntegerFromReqeust(request, "sourceId");
		int receiverId = HttpUtils.getIntegerFromReqeust(request, "receiverId");
		Message message = new Message(receiverId, false, type, sourceId);
		messageService.addMessage(message);
		// return "messagelist";
		return "";
	}

	@RequestMapping(value = "message/delete.do")
	public String deleteMessage(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		Message message = messageService.getMessageById(id);
		if (message != null) {
			messageService.deleteMessage(message);
		}
		return "messagelist";
	}

}
