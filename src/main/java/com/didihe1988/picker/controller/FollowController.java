package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class FollowController {

	@Autowired
	private FollowService followService;

	@RequestMapping(value = "follow/list_byfollowerid.do")
	public String listByFollowerId(HttpServletRequest request, ModelMap modelMap) {
		int followerId = HttpUtils.getIntegerFromReqeust(request, "followerId");
		List<Follow> followList = followService
				.getFollowByFollowerId(followerId);
		modelMap.addAttribute("followList", followList);
		for (int i = 0; i < followList.size(); i++) {
			System.out.println(followList.get(i).toString());
		}
		return "followlist";
	}

	@RequestMapping(value = "follow/list_byfolloweduserid.do")
	public String listByFollowedUserId(HttpServletRequest request,
			ModelMap modelMap) {
		int followedUserId = HttpUtils.getIntegerFromReqeust(request,
				"followedUserId");
		List<Follow> followList = followService
				.getFollowByFollowedUserId(followedUserId);
		modelMap.addAttribute("followList", followList);
		for (int i = 0; i < followList.size(); i++) {
			System.out.println(followList.get(i).toString());
		}
		return "followlist";
	}

	@RequestMapping(value = "follow/add.do")
	public String addFollow(HttpServletRequest request) {
		int sourceType = HttpUtils.getIntegerFromReqeust(request, "sourceType");
		int followerId = HttpUtils.getSessionUser(request).getId();
		int sourceId = HttpUtils.getIntegerFromReqeust(request, "sourceId");
		Follow follow = new Follow(sourceType, followerId, sourceId);
		followService.addFollow(follow);
		return "followlist";
	}

	@RequestMapping(value = "follow/delete.do")
	public String deleteFollow(HttpServletRequest request) {
		int id = HttpUtils.getIntegerFromReqeust(request, "id");
		Follow follow = followService.getFollowById(id);
		if (follow == null) {
			return "followlist";
		}
		followService.deleteFollow(follow);
		return "followlist";
	}

}
