package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;

@Controller
public class QuestionController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/question/{id}")
	public String question(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (feedService.isFeedExistsById(id)) {
			Feed feed = feedService.getFeedById(id);
			UserDp userDp = userService.getUserDpByUserId(feed.getUserId(),
					false);
			model.addAttribute("user", userDp);
			model.addAttribute("question", feed);
			return "question";
		} else {
			return "error";
		}

	}
}
