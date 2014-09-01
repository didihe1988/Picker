package com.didihe1988.picker.controller;

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
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/json/answer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswer(@PathVariable int id) {
		Answer answer = answerService.getAnswerById(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER, answer);
	}

	/**
	 * @description 删除该条回答
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteAnswer(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = answerService.deleteAnswerById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 该回答下的评论
	 */
	@RequestMapping(value = "/json/answer/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_ANSWER);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description 用户赞了该评论
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementAnswerFavorite(id, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的回答
			 */
			Answer answer = answerService.getAnswerById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					answer.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(answer.getReplierId(),
					Message.MESSAGE_YOUR_ANSWER_FAVORITED, userId, userName,
					id, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的问题
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_ANSWER, userId, userName,
					id, relatedSourceContent);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/answer/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decrementAnswerFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/answer/add", method = RequestMethod.POST)
	public String add(@RequestBody Answer answer, HttpServletRequest request) {
		/*
		 * 添加回答
		 */
		int status = answerService.addAnswer(answer);
		if (status == Status.SUCCESS) {
			addAnswerMessage(answer, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/answer/update", method = RequestMethod.POST)
	public String update(@RequestBody Answer answer, HttpServletRequest request) {
		/*
		 * 编辑回答
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int status = answerService.updateAnswer(answer, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addAnswerMessage(Answer answer, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int askerId = questionService.getQuestionById(answer.getQuestionId())
				.getAskerId();
		String userName = HttpUtils.getSessionUserName(request);
		/*
		 * answerId 需要查询获得
		 */
		int answerId = answerService.getLatestAnswerIdByQuestionId(answer
				.getQuestionId());

		String relatedSourceContent = StringUtils.confineStringLength(
				answer.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * 通知提问者XXX回答了您的问题
		 */
		messageService.addMessageByRecerver(askerId,
				Message.MESSAGE_YOUR_QUESTION_UPDATE, userId, userName,
				answerId, relatedSourceContent);
		/*
		 * 通知关注者 小明 (被关注者)回答了一个问题
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ANSWER_QUESTION, userId, userName,
				answerId, relatedSourceContent);
	}

}
