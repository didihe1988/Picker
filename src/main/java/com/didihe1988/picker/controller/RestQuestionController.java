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
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RestQuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FollowService followService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/json/question/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestion(@PathVariable int id) {
		Question question = questionService.getQuestionById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_QUESTION,
				question);
	}

	/**
	 * @description 该问题下的回答
	 */
	@RequestMapping(value = "/json/question/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id) {
		List<Answer> list = answerService.getAnswerListByQuestionId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 该问题下的评论
	 */
	@RequestMapping(value = "/json/question/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_QUESTION);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description 删除该条问题
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/question/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteQuestion(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = questionService.deleteQuestionById(id, userId);
		return JsonUtils.getJsonObjectString("status", status);
	}

	/**
	 * @description 获得关注该问题的用户列表
	 */
	@RequestMapping(value = "/json/question/{id}/followers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowers(@PathVariable int id) {
		List<Follow> followList = followService.getFollowListByQuestionId(id);
		List<UserDp> list = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp userDp = userService.getUserDpByUserId(follow
					.getFollowerId());
			list.add(userDp);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, list);
	}

	/**
	 * @description 关注该问题
	 */
	@RequestMapping(value = "/json/question/{id}/follow", method = RequestMethod.GET)
	public String follow(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		Follow follow = new Follow(Follow.FOLLOW_QUESTION, userId, id);
		int status = followService.addFollow(follow);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 取消关注该问题
	 */
	/*
	 * 这个不知道行不行
	 */
	@RequestMapping(value = "/json/question/{id}/withdraw_follow", method = RequestMethod.GET)
	public String withdrawFollow(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = followService.deleteFollow(Follow.FOLLOW_QUESTION, userId,
				id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户赞了该问题
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/json/question/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementQuestionFavorite(id, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的问题
			 */
			Question question = questionService.getQuestionById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					question.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(question.getAskerId(),
					Message.MESSAGE_YOUR_QUESTION_FAVORITED, userId, userName,
					id, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的问题
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_QEUSTION, userId,
					userName, id, relatedSourceContent);
			/*
			 * 用户动态
			 */
			messageService.addMessageByRecerver(Message.NULL_receiverId,
					Message.MESSAGE_USER_FAVORITE_QUESTION, userId, userName,
					id, relatedSourceContent);
		}

		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户取消了赞
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/question/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUser(request).getId();
		int status = favoriteService.decrementQuestionFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/question/add", method = RequestMethod.POST)
	public String add(@RequestBody Question question, HttpServletRequest request) {
		/*
		 * 添加问题
		 */
		question.setDate(new Date());
		int status = questionService.addQuestion(question);
		if (status == Status.SUCCESS) {
			addQuestionMessage(question, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/question/update", method = RequestMethod.POST)
	public String update(@RequestBody Question question,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = questionService.updateQuestion(question, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addQuestionMessage(Question question,
			HttpServletRequest request) {
		/*
		 * 通知关注者 小明 (被关注者)提出了一个问题
		 */
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int questionId = questionService.getLatestQuestionIdByBookId(question
				.getBookId());
		String relatedSourceContent = StringUtils.confineStringLength(
				question.getContent(), Constant.MESSAGE_LENGTH);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ASKQUESTION, userId, userName,
				questionId, relatedSourceContent);
		/*
		 * 用户动态
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDQUESTION, userId, userName, questionId,
				relatedSourceContent);
	}

}
