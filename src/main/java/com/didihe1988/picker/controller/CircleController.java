package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.dp.UserCircleDp;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.model.json.CircleJson;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@Controller
public class CircleController {
	@Autowired
	private CircleService circleService;

	@Autowired
	private CircleMemberService circleMemberService;

	@Autowired
	private UserService userService;

	/**
	 * @description group.jsp
	 */
	@RequestMapping(value = "/group/{id}")
	public String getGroupDetail(@PathVariable int id, Model model,
			HttpServletRequest request) {
		model.addAttribute("circle", circleService.getCircleById(id));
		/*
		 * model.addAttribute("userList",
		 * circleMemberService.getCircleWebDpListByCircleId(id));
		 */
		model.addAttribute("userList",
				getUserList(id, HttpUtils.getSessionUserId(request)));
		return "group";
	}

	private List<UserCircleDp> getUserList(int circleId, int curUserId) {

		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByCircleId(circleId);
		List<UserCircleDp> list = new ArrayList<UserCircleDp>();

		for (CircleMember circleMember : circleMembers) {
			UserDp userDp = userService.getUserDpByUserId(
					circleMember.getMemberId(), curUserId);
			List<Circle> circleList = circleMemberService
					.getCircleListByMemberId(circleMember.getMemberId());
			UserCircleDp userCircleDp = new UserCircleDp(userDp, circleList);
			list.add(userCircleDp);
		}
		return list;
	}


}
