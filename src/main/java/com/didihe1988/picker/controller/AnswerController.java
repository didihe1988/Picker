package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/answer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswer(@PathVariable int id) {
		Answer answer = answerService.getAnswerById(id);
		return JsonUtils.getJsonObjectString("answer", answer);
	}

	@RequestMapping(value = "/answer/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteAnswer(@PathVariable int id) {
		int status = answerService.deleteAnswerById(id);
		return JsonUtils.getJsonObjectString("status", status);
	}

	/**
	 * @description 该回答下的评论
	 */
	@RequestMapping(value = "/answer/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_ANSWER);
		return JsonUtils.getJsonObjectString("commentList", list);
	}

	@RequestMapping(value = "/answer/add.do")
	public String add(HttpServletRequest request) {
		/*
		 * 添加回答
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int questionId = HttpUtils.getIntegerFromReqeust(request, "questionId");
		String content = HttpUtils.getStringFromReqeust(request, "content");
		Answer answer = new Answer(questionId, userId, content);
		answerService.addAnswer(answer);

		int askerId = questionService.getQuestionById(answer.getQuestionId())
				.getAskerId();
		String userName = HttpUtils.getSessionUserName(request);
		int answerId = answerService.getLatestAnswerIdByQuestionId(questionId);

		String relatedSourceContent = StringUtils.confineStringLength(content,
				Constant.MESSAGE_LENGTH);
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

		return "";

	}

	@RequestMapping(value = "/answer/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		int answerId = HttpUtils.getIntegerFromReqeust(request, "answerId");
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementAnswerFavorite(answerId, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的回答
			 */
			Answer answer = answerService.getAnswerById(answerId);
			String relatedSourceContent = StringUtils.confineStringLength(
					answer.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(answer.getReplierId(),
					Message.MESSAGE_YOUR_ANSWER_FAVORITED, userId, userName,
					answerId, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的问题
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_ANSWER, userId, userName,
					answerId, relatedSourceContent);
		}

		return "";
	}

}
