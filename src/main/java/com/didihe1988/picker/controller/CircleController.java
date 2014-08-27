package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class CircleController {
	@Autowired
	private CircleService circleService;

	@Autowired
	private UserService userService;

	@Autowired
	private CircleMemberService circleMemberService;

	@RequestMapping(value = "/circle/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircle(@PathVariable int id) {
		Circle circle = circleService.getCircleById(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_CIRCLE, circle);
	}

	/**
	 * @description 圈子里的用户
	 */
	@RequestMapping(value = "/circle/{id}/members", method = RequestMethod.GET, headers = "Accept=application/json")
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

}
