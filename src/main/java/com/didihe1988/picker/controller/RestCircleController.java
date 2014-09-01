package com.didihe1988.picker.controller;

import java.util.ArrayList;
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
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RestCircleController {
	@Autowired
	private CircleService circleService;

	@Autowired
	private UserService userService;

	@Autowired
	private CircleMemberService circleMemberService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/json/circle/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircle(@PathVariable int id) {
		Circle circle = circleService.getCircleById(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_CIRCLE, circle);
	}

	/**
	 * @description 圈子里的用户
	 */
	@RequestMapping(value = "/json/circle/{id}/members", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getMembers(@PathVariable int id) {
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByCircleId(id);
		List<UserDp> list = new ArrayList<UserDp>();
		for (CircleMember circleMember : circleMembers) {
			UserDp userDp = userService.getUserDpByUserId(circleMember
					.getMemberId());
			list.add(userDp);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, list);
	}

	/**
	 * 添加一个圈子
	 */
	@RequestMapping(value = "/json/circle/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String add(@RequestBody Circle circle, HttpServletRequest request) {
		/*
		 * 添加问题
		 */
		int status = circleService.addCircle(circle);
		if (status == Status.SUCCESS) {
			addCircleMessage(circle, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addCircleMessage(Circle circle, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		String relatedSourceContent = StringUtils.confineStringLength(
				circle.getName(), Constant.MESSAGE_LENGTH);
		int circleId = circleService.getLatestCircleIdByEstablisherId(circle
				.getEstablisherId());
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ADDCIRCLE, userId, userName, circleId,
				relatedSourceContent);
	}

	/*
	 * 加入一个圈子
	 */
	@RequestMapping(value = "/json/circle/{id}/join", method = RequestMethod.GET, headers = "Accept=application/json")
	public String join(@PathVariable int id, HttpServletRequest request) {

		int userId = HttpUtils.getSessionUserId(request);
		CircleMember circleMember = new CircleMember(id, userId);
		int status = circleMemberService.addCircleMember(circleMember);
		if (status == Status.SUCCESS) {
			joinCircleMessage(id, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/*
	 * 离开一个圈子
	 */
	@RequestMapping(value = "/json/circle/{id}/withdraw_join", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawJoin(@PathVariable int id, HttpServletRequest request) {
		/*
		 * 由于CricleMember对外是透明的，所以不可能获得它的id
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int status = circleMemberService.deleteCircleMember(userId, id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void joinCircleMessage(int circleId, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		Circle circle = circleService.getCircleById(circleId);
		String relatedSourceContent = StringUtils.confineStringLength(
				circle.getName(), Constant.MESSAGE_LENGTH);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_JOINCIRCLE, userId, userName,
				circleId, relatedSourceContent);
	}
}
