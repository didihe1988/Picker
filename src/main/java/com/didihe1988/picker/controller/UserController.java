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
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.dp.FullMessage;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.model.form.LoginForm;
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

	@Autowired
	private MessageService messageService;

	/**
	 * @description �û���½ enter.jsp
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm loginForm, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if (!userService.hasMatchUser(loginForm.getEmail(),
				loginForm.getPassword())) {
			// ������Լ�toast
			model.addAttribute("email", loginForm.getEmail());
			return "enter";
		} else {
			User user = userService.getUserByEmail(loginForm.getEmail());
			/*
			 * ���µ�½ʱ��
			 */
			user.setLastVisit(new Date());
			userService.updateUser(user);
			HttpUtils.setSessionUserId(request, user.getId());

			try {
				response.sendRedirect("/user/" + user.getId());
				// response.sendRedirect("/picker/test");
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
		if (userService.isUserExistsById(id)) {
			int curUserId = HttpUtils.getSessionUserId(request);
			addBaseAttribute(id, model, curUserId);
			model.addAttribute("bookList", getBooks(id));
			List<FullMessage> list= messageService
					.getFullMessageByUserIdAndFilter(curUserId,
							Message.Filter.MESSAGE_FOOTPRINT);
			System.out.println(list);
			model.addAttribute("messageList",list);
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
	 * @description �ش��������
	 * @condition request����isFavorite
	 */
	@RequestMapping(value = "/user/{id}/answers/{page}")
	public String getAnswers(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
			List<AnswerDp> list = answerService.getAnswerDpListByReplierId(id,
					HttpUtils.getSessionUserId(request));
			model.addAttribute("answerList", list);
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description �ʹ�������
	 * @condition request����isFollow
	 */
	@RequestMapping(value = "/user/{id}/questions/{page}")
	public String getQuestions(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
			List<FeedDp> list = feedService.getFeedDpListByUserId(id,
					Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request));
			model.addAttribute("questionList", list);
			return "user";
		} else {
			return "error";
		}

	}

	/**
	 * @description д���ıʼ�
	 * @condition request����isFollow
	 */
	@RequestMapping(value = "/user/{id}/notes/{page}")
	public String getNotes(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (userService.isUserExistsById(id)) {
			addBaseAttribute(id, model, HttpUtils.getSessionUserId(request));
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
	 * @description ��ʾ�ҵ���Ϣ
	 */
	@RequestMapping(value = "/message")
	public String message(Model model, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		model.addAttribute("messageList", messageService
				.getMessageDpByUserIdAndFilter(userId,
						Message.Filter.MESSAGE_RELATED));
		return "message";
	}

	/**
	 * @description ��ʾ�ҵ�Ȧ��
	 */
	@RequestMapping(value = "/group")
	public String getUserGroup(Model model, HttpServletRequest request) {
		int curUserId = HttpUtils.getSessionUserId(request);
		model.addAttribute("circleList",
				circleMemberService.getCircleWebDpListByMemberId(curUserId));
		return "group_index";
	}

	/**
	 * @description ��ʾ�ҵĶ�̬ ��ʱ
	 */
	@RequestMapping(value = "/dynamic")
	public String dynamic(Model model, HttpServletRequest request) {
		int curUserId = HttpUtils.getSessionUserId(request);
		model.addAttribute("messageList",
				messageService.getDynamicByUserId(curUserId));
		model.addAttribute("bookList", getBooks(curUserId));
		return "index";
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
