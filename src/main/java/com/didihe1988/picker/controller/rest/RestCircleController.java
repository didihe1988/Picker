package com.didihe1988.picker.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.CircleDp;
import com.didihe1988.picker.model.dp.UserDp;
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

	/*
	 * 查看circle的详细内容
	 */
	@RequestMapping(value = "/json/circle/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircle(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Circle circle = circleService.getCircleById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_CIRCLE,
				circle);
	}

	@RequestMapping(value = "/json/circle/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircleDp(@PathVariable int id, HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		CircleDp circleDp = circleService.getCircleDpById(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_CIRCLE,
				circleDp);
	}

	/**
	 * @description 圈子里的用户
	 */
	@RequestMapping(value = "/json/circle/{id}/members", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getMembers(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByCircleId(id);
		List<UserDp> list = new ArrayList<UserDp>();
		int curUserId = HttpUtils.getSessionUserId(request);
		for (CircleMember circleMember : circleMembers) {
			UserDp userDp = userService.getUserDpByUserId(
					circleMember.getMemberId(), curUserId);
			list.add(userDp);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, list);
	}

	/**
	 * 添加一个圈子
	 */
	@RequestMapping(value = "/json/circle/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String webAdd(@RequestBody Circle circle, HttpServletRequest request) {
		/*
		 * 添加问题
		 */
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (!circle.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setCircle(circle, request);
		int status = circleService.addCircle(circle);
		if (status == Status.SUCCESS) {
			addCircleMessage(circle, request);

		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void setCircle(Circle circle, HttpServletRequest request) {
		circle.setEstablishTime(new Date());
		circle.setEstablisherId(HttpUtils.getSessionUserId(request));
		/*
		 * 可能有风险，待验证
		 */
		circle.setMemberNum(1);
	}

	/**
	 * 添加一个圈子
	 */
	@RequestMapping(value = "/circle/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String add(@ModelAttribute Circle circle, HttpServletRequest request) {
		/*
		 * 添加问题
		 */
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (!circle.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setCircle(circle, request);
		int status = circleService.addCircle(circle);
		if (status == Status.SUCCESS) {
			addCircleMessage(circle, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addCircleMessage(Circle circle, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		User producer = userService.getUserById(userId);
		String relatedSourceContent = StringUtils.confineStringLength(
				circle.getName(), Constant.MESSAGE_LENGTH);
		int circleId = circleService.getLatestCircleIdByEstablisherId(circle
				.getEstablisherId());
		String extraContent = StringUtils.confineStringLength(
				circle.getDescribe(), Constant.MESSAGE_LENGTH);

		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ADDCIRCLE, producer, circleId,
				relatedSourceContent, extraContent, Message.NULL_parentId);

		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDCIRCLE, producer, circleId,
				relatedSourceContent, extraContent, Message.NULL_parentId);
	}

	/*
	 * 加入一个圈子
	 */
	@RequestMapping(value = "/json/circle/{id}/join", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String join(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
	@RequestMapping(value = "/json/circle/{id}/withdraw_join", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String withdrawJoin(@PathVariable int id, HttpServletRequest request) {
		/*
		 * 由于CricleMember对外是透明的，所以不可能获得它的id
		 */
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = circleMemberService.deleteCircleMember(userId, id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void joinCircleMessage(int circleId, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		User producer = userService.getUserById(userId);
		Circle circle = circleService.getCircleById(circleId);
		String relatedSourceContent = StringUtils.confineStringLength(
				circle.getName(), Constant.MESSAGE_LENGTH);
		String extraContent = StringUtils.confineStringLength(
				circle.getDescribe(), Constant.MESSAGE_LENGTH);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_JOINCIRCLE, producer, circleId,
				relatedSourceContent, extraContent, Message.NULL_parentId);
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_JOINCIRCLE, producer, circleId,
				relatedSourceContent, extraContent, Message.NULL_parentId);

	}

}
