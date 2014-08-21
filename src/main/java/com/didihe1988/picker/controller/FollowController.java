package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class FollowController {

	@Autowired
	private FollowService followService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageFactory messageFactory;

	@RequestMapping(value = "/follow/list_byfollowerid.do")
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

	@RequestMapping(value = "/follow/list_byfolloweduserid.do")
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

	@RequestMapping(value = "/follow/listall_fortest.do")
	public String listAllForTest(ModelMap modelMap) {
		List<Follow> followList = followService.getAllFollowForTest();
		System.out.print(followList);
		modelMap.addAttribute("followList", followList);
		return "followlist";
	}

	@RequestMapping(value = "/follow/add.do")
	public String addFollow(HttpServletRequest request) {
		/*
		 * 添加follow
		 */
		int sourceType = HttpUtils.getIntegerFromReqeust(request, "sourceType");
		int sourceId = HttpUtils.getIntegerFromReqeust(request, "sourceId");
		int followerId = HttpUtils.getSessionUserId(request);
		int userId = followerId;
		String userName = HttpUtils.getSessionUserName(request);
		// 自己不能关注自己
		if ((sourceId == followerId) && (sourceType == Follow.FOLLOW_USER)) {
			return "followlist";
		}
		Follow follow = new Follow(sourceType, followerId, sourceId);
		followService.addFollow(follow);

		/*
		 * XXX关注了您
		 */
		if (sourceType == Follow.FOLLOW_USER) {
			messageService.addMessageByRecerver(userId,
					Message.MESSAGE_USER_FOLLOWED, followerId, userName,
					sourceId, Message.NULL_RelatedSourceContent);
		}

		// 如果是关注一个问题，那么给他的follower一个message
		if (sourceType == Follow.FOLLOW_QUESTION) {
			/*
			 * userId:找到follower userId：mainSourceType
			 * sourceId:relatedSourceType
			 */
			messageFactory.addMessage(userId, userId, sourceId,
					Message.MESSAGE_FOLLOWED_FOLLOWQUESTION);
		}
		return "/follow/listall_fortest.do";
	}

	@RequestMapping(value = "/follow/delete.do")
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
