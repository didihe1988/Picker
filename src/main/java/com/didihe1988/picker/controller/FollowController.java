package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class FollowController {

	@Autowired
	private FollowService followService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FeedService feedService;

	@RequestMapping(value = "/json/follow/list/follower/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowListByFollowerId(@PathVariable int id) {
		List<Follow> followList = followService.getFollowListByFollowerId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_FOLLOW_LIST,
				followList);
	}

	@RequestMapping(value = "/json/follow/list/followeduser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowListByFollowedUserId(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_FOLLOW_LIST,
				followList);
	}

	@RequestMapping(value = "/json/follow/list_byfolloweduserid.do")
	public String listByFollowedUserId(HttpServletRequest request,
			ModelMap modelMap) {
		int followedUserId = HttpUtils.getIntegerFromReqeust(request,
				"followedUserId");
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(followedUserId);
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

		/*
		 * 通知关注者 小明 (被关注者)关注了一个XXX问题
		 */
		if (sourceType == Follow.FOLLOW_QUESTION) {
			Feed feed = feedService.getFeedById(sourceId);
			String relatedSourceContent = StringUtils.confineStringLength(
					feed.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FOLLOWQUESTION, userId, userName,
					sourceId, relatedSourceContent);
		}
		/*
		 * 用户动态
		 */
		// messageService.addMessageByRecerver(Message.NULL_receiverId,
		// Message.MESSAGE_USER_FOLLOW_OTHER,userId, userName, relatedSourceId,
		// relatedSourceContent);

		return "/follow/listall_fortest.do";
	}

}
