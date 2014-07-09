package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.service.FollowService;

@Controller
public class FollowController extends BaseController {

	@Autowired
	private FollowService followService;

	@RequestMapping(value = "follow/list_byfollowerid.do")
	public String listByFollowerId(HttpServletRequest request, ModelMap modelMap) {
		int followerId = Integer.parseInt(request.getParameter("followerId"));
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
		int followedUserId = Integer.parseInt(request
				.getParameter("followedUserId"));
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
		int sourceType = Integer.parseInt(request.getParameter("sourceType"));
		int followerId = getSessionUser(request).getId();
		int sourceId = Integer.parseInt(request.getParameter("sourceId"));
		Follow follow = new Follow(sourceType, followerId, sourceId);
		followService.addFollow(follow);
		return "followlist";
	}

	@RequestMapping(value = "follow/delete.do")
	public String deleteFollow(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Follow follow = followService.getFollowById(id);
		if (follow == null) {
			return "followlist";
		}
		followService.deleteFollow(follow);
		return "followlist";
	}

}
