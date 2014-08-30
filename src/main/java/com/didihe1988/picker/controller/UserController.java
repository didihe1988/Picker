package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private FollowService followService;

	@Autowired
	private NoteService noteService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CircleService circleService;

	@Autowired
	private CircleMemberService circleMemberService;

	/**
	 * @description user.jsp
	 */
	@RequestMapping(value = "/user")
	public String user() {
		return "user";
	}

	/**
	 * @description user.jsp test rest
	 */
	@RequestMapping(value = "/user/{id}")
	public String test(@PathVariable int id, Model model) {
		UserDp user = userService.getUserDpByUserId(id);
		List<Circle> circleList = circleMemberService
				.getCircleListByMemberId(id);
		model.addAttribute("user", user);
		/*
		model.addAttribute("circleList", circleList);
		model.addAttribute("followerList", getFollowers(id));
		model.addAttribute("followeeList", getFollowees(id));
		model.addAttribute("bookList", getBooks(id));*/
		return "user";
	}

	private List<UserDp> getFollowers(int userId) {
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getFollowerId());
			userList.add(user);
		}
		return userList;
	}

	private List<UserDp> getFollowees(int userId) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByUser(userId);
		List<UserDp> userList = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp user = userService.getUserDpByUserId(follow.getSourceId());
			userList.add(user);
		}
		return userList;
	}

	private List<Book> getBooks(int userId) {
		List<Bought> boughtList = boughtService.getBoughtByUserId(userId);
		List<Book> bookList = new ArrayList<Book>();
		for (Bought bought : boughtList) {
			Book book = bookService.getBookById(bought.getBookId());
			bookList.add(book);
		}
		return bookList;
	}

}
