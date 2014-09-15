package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.AnswerDp;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.LoginForm;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.model.dp.FeedDp;
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

@RestController
public class RestUserController {
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
	 * @description 用户登陆
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public String login(@RequestBody LoginForm loginForm,
			HttpServletRequest request) {
		int status = Status.ERROR;
		if (userService.hasMatchUser(loginForm.getEmail(),
				loginForm.getPassword())) {
			status = Status.NOT_EXISTS;
		} else {
			status = Status.SUCCESS;
			User user = userService.getUserByEmail(loginForm.getEmail());
			/*
			 * 更新登陆时间
			 */
			user.setLastVisit(new Date());
			userService.updateUser(user);
			HttpUtils.setSessionUserId(request, user.getId());
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 个人信息
	 */
	@RequestMapping(value = "/json/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUser(@PathVariable int id, HttpServletRequest request) {
		UserDp userDp = userService.getUserDpByUserId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils
				.getJsonObjectStringFromModel(Constant.KEY_USER, userDp);
	}

	@RequestMapping(value = "/json/user", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUser(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		UserDp userDp = userService.getUserDpByUserId(userId, userId);
		return JsonUtils
				.getJsonObjectStringFromModel(Constant.KEY_USER, userDp);
	}

	/**
	 * @description 回答过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id) {
		List<Answer> list = answerService.getAnswerListByReplierId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 回答过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/answerdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswerDps(@PathVariable int id, HttpServletRequest request) {
		List<AnswerDp> list = answerService.getAnswerDpListByReplierId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 问过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		// List<Question> list = questionService.getQuestionListByAskerId(id);
		List<Feed> list = feedService.getFeedListByUserId(id,
				Feed.TYPE_QUESTION);
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 问过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/questiondps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionDps(@PathVariable int id,
			HttpServletRequest request) {
		List<FeedDp> list = feedService.getFeedDpListByUserId(id,
				Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 写过的笔记 自己的笔记显示全部 别人的只显示public部分
	 */
	@RequestMapping(value = "/json/user/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNotes(@PathVariable int id, HttpServletRequest request) {
		List<Feed> list = new ArrayList<Feed>();
		/*
		 * int curUserId = HttpUtils.getSessionUserId(request); if (curUserId ==
		 * id) { list = noteService.getALlNoteListByUserId(id); } list =
		 * noteService.getPublicNoteListByUserId(id);
		 */
		list = feedService.getFeedListByUserId(id, Feed.TYPE_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 写过的笔记 自己的笔记显示全部 别人的只显示public部分
	 */
	@RequestMapping(value = "/json/user/{id}/notedps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNoteDps(@PathVariable int id, HttpServletRequest request) {
		List<FeedDp> list = new ArrayList<FeedDp>();
		/*
		 * int userId = HttpUtils.getSessionUserId(request); if (userId == id) {
		 * list = noteService.getALlNoteDpListByUserId(id,
		 * HttpUtils.getSessionUserId(request)); }
		 */
		int curUserId = HttpUtils.getSessionUserId(request);
		list = feedService.getFeedDpListByUserId(id, Feed.TYPE_NOTE, curUserId);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 关注了XXX
	 */
	@RequestMapping(value = "/json/user/{id}/followees", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowees(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByUser(id);
		List<User> userList = new ArrayList<User>();
		for (Follow follow : followList) {
			User user = userService.getUserById(follow.getSourceId());
			userList.add(user);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, userList);
	}

	/**
	 * @description 关注者 被XXX关注
	 */
	@RequestMapping(value = "/json/user/{id}/followers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowers(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(id);
		List<User> userList = new ArrayList<User>();
		for (Follow follow : followList) {
			User user = userService.getUserById(follow.getFollowerId());
			userList.add(user);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, userList);
	}

	/**
	 * @description 关注的问题
	 */
	@RequestMapping(value = "/json/user/{id}/questions_followed", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionsFollowed(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByQuestion(id);
		List<Feed> feedList = new ArrayList<Feed>();
		for (Follow follow : followList) {
			Feed feed = feedService.getFeedById(follow.getSourceId());
			feedList.add(feed);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST,
				feedList);
	}

	/**
	 * @description 添加的书籍
	 */
	@RequestMapping(value = "/json/user/{id}/books", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBooks(@PathVariable int id) {
		List<Bought> boughtList = boughtService.getBoughtByUserId(id);
		List<Book> bookList = new ArrayList<Book>();
		for (Bought bought : boughtList) {
			Book book = bookService.getBookById(bought.getBookId());
			bookList.add(book);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_BOOK_LIST, bookList);
	}

	/**
	 * @description 加入的圈子
	 */
	@RequestMapping(value = "/json/user/{id}/circles", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircles(@PathVariable int id) {
		List<Circle> circleList = circleMemberService
				.getCircleListByMemberId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_CIRCLE_LIST,
				circleList);
	}

	/**
	 * @description 关注该用户
	 */
	@RequestMapping(value = "/json/user/{id}/follow", method = RequestMethod.GET)
	public String follow(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		Follow follow = new Follow(Follow.FOLLOW_USER, userId, id);
		int status = followService.addFollow(follow);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 取消关注该用户
	 */
	@RequestMapping(value = "/json/user/{id}/withdraw_follow", method = RequestMethod.GET)
	public String withdrawFollow(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = followService.deleteFollow(Follow.FOLLOW_USER, userId, id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

}
