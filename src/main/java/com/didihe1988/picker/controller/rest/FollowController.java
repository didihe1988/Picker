package com.didihe1988.picker.controller.rest;

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
import com.didihe1988.picker.model.User;
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
}
