package com.didihe1988.picker.controller;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.UserService;

@RestController
public class CircleController {
	@Autowired
	private CircleService circleService;

	@Autowired
	private UserService userService;

	@Autowired
	private CircleMemberService circleMemberService;

	@RequestMapping(value = "/circle/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Circle getCircle(@PathVariable int id) {
		return circleService.getCircleById(id);
	}

	/**
	 * @description 圈子里的用户
	 */
	@RequestMapping(value = "/circle/{id}/members", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getMembers(@PathVariable int id) {
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByCircleId(id);
		List<User> userList = new ArrayList<User>();
		for (CircleMember circleMember : circleMembers) {
			User user = userService.getUserById(circleMember.getMemberId());
			userList.add(user);
		}
		return userList;
	}

}
