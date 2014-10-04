package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Dialog;
import com.didihe1988.picker.model.PrivateMessage;
import com.didihe1988.picker.model.dp.PrivateMessageDp;
import com.didihe1988.picker.model.dp.PrivateMessageSum;
import com.didihe1988.picker.service.DialogService;
import com.didihe1988.picker.service.PrivateMessageService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestPrivateMessageController {
	@Autowired
	private PrivateMessageService PMService;

	@Autowired
	private DialogService dialogService;

	@RequestMapping(value = "/json/pmessage/send", method = RequestMethod.POST, headers = "Accept=application/json")
	public String send(@RequestBody PrivateMessage privateMessage,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (!privateMessage.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setPM(privateMessage, request);
		int status = PMService.addPrivateMessage(privateMessage);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void setPM(PrivateMessage privateMessage, HttpServletRequest request) {
		int senderId = HttpUtils.getSessionUserId(request);
		privateMessage.setSenderId(senderId);
		privateMessage.setTime(new Date());
		setDialogId(privateMessage);
	}

	private void setDialogId(PrivateMessage privateMessage) {
		long dialogId = PMService.getDialogIdByUserId(
				privateMessage.getSenderId(), privateMessage.getReceiverId());
		if (dialogId != -1) {
			privateMessage.setDialogId(dialogId);
			dialogService.incrementCount(dialogId);
		} else {
			/*
			 * 构造函数内count=1 有一定风险
			 */
			dialogService.addDialog(new Dialog());
			long newDialogId = dialogService.getLatestDialogId();
			privateMessage.setDialogId(newDialogId);
		}
	}

	@RequestMapping(value = "/json/pmessage", method = RequestMethod.GET, headers = "Accept=application/json")
	public String pmessage(HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		int userId = HttpUtils.getSessionUserId(request);
		List<PrivateMessageSum> list = PMService
				.getPrivateMessageSumByUserId(userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_PRIVATEMESSAGE_LIST,
				list);
	}

	@RequestMapping(value = "/json/pmessage/{dialogId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String pmessageByDialogId(@PathVariable Long dialogId,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		int userId = HttpUtils.getSessionUserId(request);
		if (!PMService.checkOperateValidation(userId, dialogId)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<PrivateMessage> list = PMService
				.getPrivateMessageByDialogId(dialogId);
		return JsonUtils.getJsonObjectString(Constant.KEY_PRIVATEMESSAGE_LIST,
				list);
	}

	@RequestMapping(value = "/json/pmessage/{dialogId}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String pmessageByDialogId_dp(@PathVariable Long dialogId,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		int userId = HttpUtils.getSessionUserId(request);
		if (!PMService.checkOperateValidation(userId, dialogId)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<PrivateMessageDp> list = PMService
				.getPrivateMessageDpList(dialogId);
		return JsonUtils.getJsonObjectString(Constant.KEY_PRIVATEMESSAGE_LIST,
				list);
	}

	@RequestMapping(value = "/json/pmessage/user/{userId}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String pmessageByUserId(@PathVariable int userId,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		int curUserId = HttpUtils.getSessionUserId(request);
		long dialogId = PMService.getDialogIdByUserId(userId, curUserId);
		List<PrivateMessageDp> list = new ArrayList<PrivateMessageDp>();
		if (dialogId != -1) {
			list = PMService.getPrivateMessageDpList(dialogId);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_PRIVATEMESSAGE_LIST,
				list);
	}

}
