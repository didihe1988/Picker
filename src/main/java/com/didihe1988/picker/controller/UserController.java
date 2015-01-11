package com.didihe1988.picker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.AnswerDp;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.display.Footprint;
import com.didihe1988.picker.model.display.UserDp;
import com.didihe1988.picker.model.form.LoginForm;
import com.didihe1988.picker.model.message.MessageFilter;
import com.didihe1988.picker.model.message.SelfRelatedFilter;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private FollowService followService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CircleService circleService;

	@Autowired
	private CircleMemberService circleMemberService;

	@Autowired
	private MessageService messageService;

	/**
	 * @description 用户登陆 enter.jsp
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm loginForm, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if (!userService.hasMatchUser(loginForm.getEmail(),
				loginForm.getPassword())) {
			// 后面可以加toast
			model.addAttribute("email", loginForm.getEmail());
			return "enter";
		} else {
			User user = userService.getUserByEmail(loginForm.getEmail());
			/*
			 * 更新登陆时间
			 */
			user.setLastVisit(new Date());
			userService.updateUser(user);
			HttpUtils.setSessionUserId(request, user.getId());

			try {
				// response.sendRedirect("/user/" + user.getId());
				response.sendRedirect("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		model.addAttribute("email", loginForm.getEmail());
		return "enter";
	}

	/**
	 * @description user.jsp
	 */
	@RequestMapping(value = "/user/{id}")
	public String getUserProfile(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (id < 1) {
			return "error";
		}
		if (userService.isUserExistsById(id)) {
			int curUserId = HttpUtils.getSessionUserId(request);
			addBaseAttribute(id, model, curUserId);
			model.addAttribute("bookList", getBooks(id));
			List<Footprint> list = messageService
					.getFootprintsByUserId(curUserId);
			model.addAttribute("messageList", list);
			return "user";
		} else {
			return "error";
		}

	}

	private void addBaseAttribute(int id, Model model, int curUserId) {
		UserDp user = userService.getUserDpByUserId(id, curUserId);
		model.addAttribute("user", user);
		model.addAttribute("circleList",
				circleMemberService.getCircleListByMemberId(id));
		model.addAttribute("followerList", getFollowers(id, curUserId));
		model.addAttribute("followeeList", getFollowees(id, curUserId));
	}

	/**
	 * @description 问过的问题
	 * @condition request用于isFollow
	 */

	@RequestMapping(value = "/user/{id}/questions/{page}")
	public String getQuestions(@PathVariable int id, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((id < 1) || (page < 0)) {
			return "error";
		}
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
			model.addAttribute("page", page);
			model.addAttribute("type", "0");
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description 回答过的问题
	 * @condition request用于isFavorite
	 */

	@RequestMapping(value = "/user/{id}/answers/{page}")
	public String getAnswers(@PathVariable int id, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((id < 1) || (page < 0)) {
			return "error";
		}
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
			model.addAttribute("page", page);
			model.addAttribute("type", "1");
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description 写过的笔记
	 * @condition request用于isFollow
	 */

	@RequestMapping(value = "/user/{id}/notes/{page}")
	public String getNotes(@PathVariable int id, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((id < 1) || (page < 0)) {
			return "error";
		}
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
			model.addAttribute("page", page);
			model.addAttribute("type", "2");
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description 显示我的消息
	 */
	@RequestMapping(value = "/message")
	public String message(Model model, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		model.addAttribute("messageList", messageService
				.getMessageDpsByUserIdAndFilter(userId,
						MessageFilter.SELF_RELATED));
		return "message";
	}

	/**
	 * @description 显示我的圈子
	 */
	@RequestMapping(value = "/group")
	public String getUserGroup(Model model, HttpServletRequest request) {
		int curUserId = HttpUtils.getSessionUserId(request);
		model.addAttribute("circleList",
				circleMemberService.getCircleWebDpListByMemberId(curUserId));
		return "group_index";
	}

	private List<UserDp> getFollowers(int userId, int curUserId) {
		final List<Follow> followList = followService
				.getFollowListByFollowedUserId(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getFollowerId(),
					curUserId);
			userList.add(user);
		}
		return userList;
	}

	private List<UserDp> getFollowees(int userId, int curUserId) {
		final List<Follow> followList = followService
				.getFollowListByFollowerIdByUser(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getSourceId(),
					curUserId);
			userList.add(user);
		}
		return userList;
	}

	private List<Book> getBooks(int userId) {
		final List<Bought> boughtList = boughtService.getBoughtByUserId(userId);
		List<Book> bookList = new ArrayList<Book>();
		for (Bought bought : boughtList) {
			Book book = bookService.getBookById(bought.getBookId());
			bookList.add(book);
		}
		return bookList;
	}

}
