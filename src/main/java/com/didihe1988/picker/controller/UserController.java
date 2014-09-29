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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.model.form.LoginForm;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

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
	
	
	/**
	 * @description 用户登陆 enter.jsp
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm loginForm,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		if (!userService.hasMatchUser(loginForm.getEmail(),
				loginForm.getPassword())) {
			//后面可以加toast
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
				//response.sendRedirect("/picker/user/"+user.getId());
				response.sendRedirect("/picker/test");
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
	public String getUserProfile(@PathVariable int id, Model model) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model);
			/*
			 * isFollow暂时设为false
			 */
			model.addAttribute("bookList", getBooks(id));
			return "user";
		} else {
			return "error";
		}

	}

	private void addBaseAttribute(int id, Model model) {
		UserDp user = userService.getUserDpByUserId(id, false);
		model.addAttribute("user", user);
		model.addAttribute("circleList",
				circleMemberService.getCircleListByMemberId(id));
		model.addAttribute("followerList", getFollowers(id));
		model.addAttribute("followeeList", getFollowees(id));
	}

	/**
	 * @description 回答过的问题
	 * @condition request用于isFavorite
	 */
	@RequestMapping(value = "/user/{id}/answers/{page}")
	public String getAnswers(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model);
			List<AnswerDp> list = answerService.getAnswerDpListByReplierId(id,
					HttpUtils.getSessionUserId(request));
			model.addAttribute("answerList", list);
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description 问过的问题
	 * @condition request用于isFollow
	 */
	@RequestMapping(value = "/user/{id}/questions/{page}")
	public String getQuestions(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model);
			List<FeedDp> list = feedService.getFeedDpListByUserId(id,
					Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request));
			model.addAttribute("questionList", list);
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
	public String getNotes(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model);
			List<FeedDp> list = feedService.getFeedDpListByUserId(id,
					Feed.TYPE_NOTE, HttpUtils.getSessionUserId(request));
			System.out.println(list);
			model.addAttribute("noteList", list);
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description 显示我的消息
	 */
	@RequestMapping(value = "/user/{id}/message")
	public String getUserMessage(@PathVariable int id) {
		return "message";
	}

	/**
	 * @description 显示我的圈子
	 */
	@RequestMapping(value = "/user/{id}/group")
	public String getUserGroup(@PathVariable int id, Model model) {
		model.addAttribute("circleList",
				circleMemberService.getCircleDpListByMemberId(id));
		return "group_index";
	}

	private List<UserDp> getFollowers(int userId) {
		final List<Follow> followList = followService
				.getFollowListByFollowedUserId(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getFollowerId(),
					false);
			userList.add(user);
		}
		return userList;
	}

	private List<UserDp> getFollowees(int userId) {
		final List<Follow> followList = followService
				.getFollowListByFollowerIdByUser(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getSourceId(),
					false);
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
