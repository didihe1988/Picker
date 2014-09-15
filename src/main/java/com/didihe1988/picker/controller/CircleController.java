package com.didihe1988.picker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.UserService;

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
	public String getGroupDetail(@PathVariable int id, Model model) {
		model.addAttribute("circle", circleService.getCircleById(id));
		//model.addAttribute("userList", getUserList(id));
		return "group";
	}
	/*
	private List<UserDp> getUserList(int circleId) {
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByCircleId(circleId);
		List<UserDp> list = new ArrayList<UserDp>();
		for (CircleMember circleMember : circleMembers) {
			UserDp userDp = userService.getUserDpByUserId(circleMember
					.getMemberId());
			list.add(userDp);
		}
		return list;
	}*/
	
}
