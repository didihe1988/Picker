package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestMessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/json/message/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getMessage(@PathVariable int id) {
		Message message = messageService.getMessageById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_MESSAGE,
				message);
	}

	@RequestMapping(value = "/json/message/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int receiverId = HttpUtils.getIntegerFromReqeust(request, "receiverId");
		List<Message> messageList = messageService
				.getMessageByReceiverId(receiverId);
		System.out.println(messageList);
		modelMap.addAttribute("messageList", messageList);
		return "messagelist";
	}

	@RequestMapping(value = "/json/message/list_type.do")
	public String listByType(HttpServletRequest request, ModelMap modelMap) {
		int receiverId = HttpUtils.getIntegerFromReqeust(request, "receiverId");
		int type = HttpUtils.getIntegerFromReqeust(request, "type");
		List<Message> messageList = messageService
				.getMessageByReceiverIdAndType(receiverId, type);
		System.out.println(messageList);
		modelMap.addAttribute("messageList", messageList);
		return "messagelist";
	}

	@RequestMapping(value = "/json/message/setchecked.do")
	public String setChecked(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		messageService.setMessageChecked(id);
		return "messagelist";
	}

	/*
	 * @RequestMapping(value = "/message/add.do") public String
	 * addMessage(HttpServletRequest request) { int type =
	 * HttpUtils.getIntegerFromReqeust(request, "type"); int mainSourceId =
	 * HttpUtils.getIntegerFromReqeust(request, "mainSourceId"); int receiverId
	 * = HttpUtils.getIntegerFromReqeust(request, "receiverId"); int
	 * relatedSourceId = HttpUtils.getIntegerFromReqeust(request,
	 * "relatedSourceId");
	 * 
	 * Message message =new Message(receiverId, type, producerId, producerName,
	 * relatedSourceId, relatedSourceContent);
	 * messageService.addMessage(message); // return "messagelist"; return ""; }
	 */

	@RequestMapping(value = "/message/delete.do")
	public String deleteMessage(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		Message message = messageService.getMessageById(id);
		if (message != null) {
			messageService.deleteMessage(message);
		}
		return "messagelist";
	}

}
