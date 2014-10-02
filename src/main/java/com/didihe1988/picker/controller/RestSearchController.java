package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@Controller
public class RestSearchController {
	@Autowired
	private UserService userService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = "/json/search/user/{username}", method = RequestMethod.GET)
	public String searchUser(@PathVariable String username,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (username.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<UserDp> list = userService.search(username,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, list);
	}

	@RequestMapping(value = "/json/search/answer/{content}", method = RequestMethod.GET)
	public String searchAnswer(@PathVariable String content,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (content.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<AnswerDp> list = answerService.search(content,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	@RequestMapping(value = "/json/search/feed/{string}", method = RequestMethod.GET)
	public String searchFeed(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<FeedDp> list = feedService.search(string,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_FEED_LIST, list);
	}

	@RequestMapping(value = "/json/search/questoin/{string}", method = RequestMethod.GET)
	public String searchQuestion(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<FeedDp> list = feedService.search(string, Feed.TYPE_QUESTION,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	@RequestMapping(value = "/json/search/note/{string}", method = RequestMethod.GET)
	public String searchNote(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<FeedDp> list = feedService.search(string, Feed.TYPE_NOTE,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}
}
