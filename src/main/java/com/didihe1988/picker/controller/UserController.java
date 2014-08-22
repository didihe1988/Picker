package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * @description ������Ϣ
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {
		return userService.getUserById(id);
	}

	/**
	 * @description �ش��������
	 */
	@RequestMapping(value = "/user/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Answer> getAnswers(@PathVariable int id) {
		return answerService.getAnswerByReplierId(id);
	}

	/**
	 * @description �ʹ�������
	 */
	@RequestMapping(value = "/user/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Question> getQuestions(@PathVariable int id) {
		return questionService.getQuestionListByAskerId(id);
	}

	/**
	 * @description д���ıʼ� �Լ��ıʼ���ʾȫ�� ���˵�ֻ��ʾpublic����
	 */
	@RequestMapping(value = "/user/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Note> getNotes(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		if (userId == id) {
			return noteService.getALlNoteListByUserId(id);
		}
		return noteService.getPublicNoteListByUserId(id);
	}

	/**
	 * @description ��ע��XXX
	 */
	@RequestMapping(value = "/user/{id}/followees", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getFollowees(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByUser(id);
		List<User> userList = new ArrayList<User>();
		for (Follow follow : followList) {
			User user = userService.getUserById(follow.getSourceId());
			userList.add(user);
		}
		return userList;
	}

	/**
	 * @description ��ע�� ��XXX��ע
	 */
	@RequestMapping(value = "/user/{id}/followers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getFollowers(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(id);
		List<User> userList = new ArrayList<User>();
		for (Follow follow : followList) {
			User user = userService.getUserById(follow.getFollowerId());
			userList.add(user);
		}
		return userList;
	}

	/**
	 * @description ��ע������
	 */
	@RequestMapping(value = "/user/{id}/questions_followed", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Question> getQuestionsFollowed(@PathVariable int id) {
		List<Follow> followList = followService
				.getFollowListByFollowerIdByQuestion(id);
		List<Question> quesionList = new ArrayList<Question>();
		for (Follow follow : followList) {
			Question question = questionService.getQuestionById(follow
					.getSourceId());
			quesionList.add(question);
		}
		return quesionList;
	}

	/**
	 * @description ��ӵ��鼮
	 */
	@RequestMapping(value = "/user/{id}/books", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Book> getBooks(@PathVariable int id) {
		List<Bought> boughtList = boughtService.getBoughtByUserId(id);
		List<Book> bookList = new ArrayList<Book>();
		for (Bought bought : boughtList) {
			Book book = bookService.getBookById(bought.getBookId());
			bookList.add(book);
		}
		return bookList;
	}

	/**
	 * @description �����Ȧ��
	 */
	@RequestMapping(value = "/user/{id}/circles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Circle> getCircles(@PathVariable int id) {
		List<CircleMember> circleMembers = circleMemberService
				.getCircleMemberListByMemberId(id);
		List<Circle> circleList = new ArrayList<Circle>();
		for (CircleMember circleMember : circleMembers) {
			Circle circle = circleService.getCircleById(circleMember
					.getCircleId());
			circleList.add(circle);
		}
		return circleList;
	}
}
