package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
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
	 * @description 个人信息
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUser(@PathVariable int id) {
		User user = userService.getUserById(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_USER, user);
	}

	/**
	 * @description 回答过的问题
	 */
	@RequestMapping(value = "/user/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id) {
		List<Answer> list = answerService.getAnswerListByReplierId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 问过的问题
	 */
	@RequestMapping(value = "/user/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		List<Question> list = questionService.getQuestionListByAskerId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 写过的笔记 自己的笔记显示全部 别人的只显示public部分
	 */
	@RequestMapping(value = "/user/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNotes(@PathVariable int id, HttpServletRequest request) {
		List<Note> list = new ArrayList<Note>();
		int userId = HttpUtils.getSessionUserId(request);
		if (userId == id) {
			list = noteService.getALlNoteListByUserId(id);
		}
		list = noteService.getPublicNoteListByUserId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 关注了XXX
	 */
	@RequestMapping(value = "/user/{id}/followees", method = RequestMethod.GET, headers = "Accept=application/json")
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
	@RequestMapping(value = "/user/{id}/followers", method = RequestMethod.GET, headers = "Accept=application/json")
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
	@RequestMapping(value = "/user/{id}/questions_followed", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionsFollowed(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByQuestion(id);
		List<Question> quesionList = new ArrayList<Question>();
		for (Follow follow : followList) {
			Question question = questionService.getQuestionById(follow
					.getSourceId());
			quesionList.add(question);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST,
				quesionList);
	}

	/**
	 * @description 添加的书籍
	 */
	@RequestMapping(value = "/user/{id}/books", method = RequestMethod.GET, headers = "Accept=application/json")
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
	@RequestMapping(value = "/user/{id}/circles", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCircles(@PathVariable int id) {
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByMemberId(id);
		List<Circle> circleList = new ArrayList<Circle>();
		for (CircleMember circleMember : circleMembers) {
			Circle circle = circleService.getCircleById(circleMember
					.getCircleId());
			circleList.add(circle);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_CIRCLE_LIST,
				circleList);
	}

	/**
	 * @description 关注该用户
	 */
	@RequestMapping(value = "/user/{id}/follow", method = RequestMethod.GET)
	public String follow(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		Follow follow = new Follow(Follow.FOLLOW_USER, userId, id);
		int status = followService.addFollow(follow);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

}
