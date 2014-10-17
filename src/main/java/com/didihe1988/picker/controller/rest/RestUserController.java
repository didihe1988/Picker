package com.didihe1988.picker.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.dp.MessageDp;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.model.form.LoginForm;
import com.didihe1988.picker.model.form.RegisterForm;
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

	@Autowired
	private MessageService messageService;

	/**
	 * @description 安卓端使用
	 */
	@RequestMapping(value = "/json/login", method = RequestMethod.POST)
	public String login(@RequestBody LoginForm loginForm,
			HttpServletRequest request) {
		int status = Status.ERROR;
		if (!userService.hasMatchUser(loginForm.getEmail(),
				loginForm.getPassword())) {
			status = Status.NOT_EXISTS;
		} else {
			User user = userService.getUserByEmail(loginForm.getEmail());
			/*
			 * 更新登陆时间
			 */
			user.setLastVisit(new Date());
			userService.updateUser(user);
			HttpUtils.setSessionUserId(request, user.getId());
			status = Status.SUCCESS;
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/register", method = RequestMethod.POST)
	public String register(@RequestBody RegisterForm registerForm,
			HttpServletRequest request) {
		if (!registerForm.checkFieldValidation()) {
			JsonUtils.getJsonObjectString(Constant.KEY_STATUS, Status.INVALID);
		}
		System.out.println(registerForm.toString());
		int status = Status.ERROR;
		if (userService.isEmailExists(registerForm.getEmail())) {
			status = Status.EXISTS;
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
		} else {
			User user = new User(registerForm.getName(),
					registerForm.getEmail(), registerForm.getPassword(),
					"/resources/image/avatar/user_avatar2.jpg", "tmp");
			/*
			 * 加密在UserService完成
			 */
			status = userService.addUser(user);
			if (status == Status.SUCCESS) {
				UserDp userDp = userService.getUserDpByEmail(registerForm
						.getEmail());
				HttpUtils.setSessionUserId(request, userDp.getId());
				return JsonUtils.getJsonObjectStringFromModel(
						Constant.KEY_USER, userDp);
			}
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 个人信息
	 */
	@RequestMapping(value = "/json/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUser(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}

		UserDp userDp = userService.getUserDpByUserId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils
				.getJsonObjectStringFromModel(Constant.KEY_USER, userDp);
	}

	@RequestMapping(value = "/json/user", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUser(HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Answer> list = answerService.getAnswerListByReplierId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 回答过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/answerdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswerDps(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<AnswerDp> list = answerService.getAnswerDpListByReplierId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 问过的问题
	 */
	@RequestMapping(value = "/json/user/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int curUserId = HttpUtils.getSessionUserId(request);
		list = feedService.getFeedDpListByUserId(id, Feed.TYPE_NOTE, curUserId);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 关注了XXX
	 */
	@RequestMapping(value = "/json/user/{id}/followees", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowees(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
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
	public String getCircles(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
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
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		Follow follow = new Follow(Follow.FOLLOW_USER, userId, id);
		int status = followService.addFollow(follow);
		if (status == Status.SUCCESS) {
			produceFollowMessage(follow);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceFollowMessage(Follow follow) {
		System.out.println("add Follow Message");
		int followedId = follow.getSourceId();
		int followerId = follow.getFollowerId();
		String followerName = userService.getUserById(followerId).getUsername();
		String followedName = userService.getUserById(followedId).getUsername();
		/*
		 * 与我相关
		 */
		messageService.addMessageByRecerver(followedId,
				Message.MESSAGE_OTHERS_FOLLOW_YOU, followerId, followerName,
				followedId, followedName, Message.NULL_ExtraContent,
				Message.NULL_parentId);
		/*
		 * 通知关注者 小明(被关注者)关注了xxx
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWEDUSER_FOLLOW, followerId, followerName,
				followedId, followedName, Message.NULL_ExtraContent,
				Message.NULL_parentId);
		/*
		 * 用户动态
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_FOLLOW_OTHER, followerId, followerName,
				followedId, followedName, Message.NULL_ExtraContent,
				Message.NULL_parentId);
	}

	/**
	 * @description 取消关注该用户
	 */
	@RequestMapping(value = "/json/user/{id}/withdraw_follow", method = RequestMethod.GET)
	public String withdrawFollow(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = followService.deleteFollow(Follow.FOLLOW_USER, userId, id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户profile里的足迹
	 */
	@RequestMapping(value = "/json/user/{id}/footprint", method = RequestMethod.GET)
	public String footprint(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<MessageDp> messageList = messageService
				.getMessageDpByUserIdAndFilter(id, Filter.MESSAGE_FOOTPRINT);
		return JsonUtils.getJsonObjectString(Constant.KEY_MESSAGE_LIST,
				messageList);
	}

	/**
	 * @description 用户关注的人的动态
	 */
	@RequestMapping(value = "/json/user/dynamic", method = RequestMethod.GET)
	public String dynamic(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		List<MessageDp> messageList = messageService
				.getMessageDpByUserIdAndFilter(userId, Filter.MESSAGE_DYNAMIC);
		return JsonUtils.getJsonObjectString(Constant.KEY_MESSAGE_LIST,
				messageList);
	}

	/**
	 * @description 与我相关的消息 如我的问题被回答了
	 */
	@RequestMapping(value = "/json/user/related_message", method = RequestMethod.GET)
	public String related(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		List<MessageDp> messageList = messageService
				.getMessageDpByUserIdAndFilter(userId, Filter.MESSAGE_RELATED);
		return JsonUtils.getJsonObjectString(Constant.KEY_MESSAGE_LIST,
				messageList);
	}

	/**
	 * 
	 * @description 回答过的问题 web端
	 */
	@RequestMapping(value = "/json/user/{userId}/answers/{page}", produces = "application/json")
	public String getAnswersByPage(@PathVariable int userId,
			@PathVariable int page) {
		if ((userId < 1) || (page < 1)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int answerNum = userService.getUserById(userId).getAnswerNum();
		int total_page = getTotalPage(answerNum);
		int current_page = page;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("answers", answerService.getAnswerJsons(userId, page));
		jsonObject.put("total_page", total_page);
		jsonObject.put("current_page", current_page);
		return jsonObject.toString();
	}

	/*
	 * http://localhost:8090/user/1/questions/1
	 */

	@RequestMapping(value = "/json/user/{userId}/questions/{page}", produces = "application/json")
	public String getQuestionsByPage(@PathVariable int userId,
			@PathVariable int page, HttpServletRequest request) {
		if ((userId < 1) || (page < 1)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int questionNum = userService.getUserById(userId).getQuestionNum();
		int total_page = getTotalPage(questionNum);
		int current_page = page;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("questions", feedService.getQuestoinJsons(userId, page));
		jsonObject.put("total_page", total_page);
		jsonObject.put("current_page", current_page);
		return jsonObject.toString();
	}

	/*
	 * http://localhost:8090/user/1/questions/1
	 */
	@RequestMapping(value = "/json/user/{userId}/notes/{page}", produces = "application/json")
	public String getNotessByPage(@PathVariable int userId,
			@PathVariable int page, HttpServletRequest request) {
		if ((userId < 1) || (page < 1)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int noteNum = userService.getUserById(userId).getNoteNum();
		int total_page = getTotalPage(noteNum);
		int current_page = page;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("notes", feedService.getNoteJsons(userId, page));
		jsonObject.put("total_page", total_page);
		jsonObject.put("current_page", current_page);
		return jsonObject.toString();
	}

	private int getTotalPage(int num) {
		return num / Constant.MAX_QUERYRESULT + 1;
	}
}
