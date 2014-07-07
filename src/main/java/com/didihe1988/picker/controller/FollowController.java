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
public class FollowController {

	@Autowired
	private FollowService followService;

	@RequestMapping(value = "follow/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int followerId = Integer.parseInt(request.getParameter("followerId"));
		List<Follow> followList = followService
				.getFollowByFollowerId(followerId);
		modelMap.addAttribute("followList", followList);
		for (int i = 0; i < followList.size(); i++) {
			System.out.println(followList.get(i).toString());
		}
		return "followlist";
	}
	/*
	 * @RequestMapping(value = "follow/list_unchecked.do") public String
	 * listUnchecked(HttpServletRequest request, ModelMap modelMap) { int
	 * followerId = Integer.parseInt(request.getParameter("followerId"));
	 * List<Follow> followList = followService
	 * .getUnckeckedFollowByFollowerId(followerId);
	 * modelMap.addAttribute("followList", followList);
	 * System.out.println("unchecked followlist"); for (int i = 0; i <
	 * followList.size(); i++) {
	 * System.out.println(followList.get(i).toString()); } return "followlist";
	 * }
	 * 
	 * @RequestMapping(value = "follow/set_ckecked.do") public String
	 * setFollowChecked(HttpServletRequest request) { int followId =
	 * Integer.parseInt(request.getParameter("followId"));
	 * followService.setFollowchecked(followId); return "followlist"; }
	 */
}
