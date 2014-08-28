package com.didihe1988.picker.controller;

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
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getComment(@PathVariable int id) {
		Comment comment = commentService.getCommentById(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT, comment);
	}

	/**
	 * @description 删除该条评论
	 * @condition session-userId
	 */
	@RequestMapping(value = "/comment/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteComment(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = commentService.deleteCommentById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户赞了该评论
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/comment/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementCommentFavorite(id, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的评论 两个函数都查询了comemnt 有待优化
			 */
			Comment comment = commentService.getCommentById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					comment.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(comment.getProducerId(),
					Message.MESSAGE_YOUR_COMMENT_FAVORITED, userId, userName,
					id, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的评论
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_COMMENT, userId,
					userName, id, relatedSourceContent);
		}

		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户取消了赞
	 * @condition session-userId
	 */
	@RequestMapping(value = "/comment/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUser(request).getId();
		int status = favoriteService.decrementCommentFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String add(@RequestBody Comment comment, HttpServletRequest request) {
		/*
		 * 添加评论
		 */
		int status = commentService.addComment(comment);
		if (status == Status.SUCCESS) {
			addCommentMessage(comment, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addCommentMessage(Comment comment, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int commentId = commentService.getLatestCommentIdByUserId(userId);
		String userName = HttpUtils.getSessionUserName(request);
		// relatedSourceContent-评论内容
		String relatedSourceContent = StringUtils.confineStringLength(
				comment.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * 小明 评论了您的问题
		 */
		if (comment.getType() == Comment.COMMENT_QUESTION) {
			Question question = questionService.getQuestionById(comment
					.getCommentedId());
			messageService.addMessageByRecerver(question.getAskerId(),
					Message.MESSAGE_YOUR_QUESTION_COMMENTED,
					comment.getProducerId(), userName, commentId,
					relatedSourceContent);
		}

		/*
		 * 小明 评论了您的回答
		 */
		if (comment.getType() == Comment.COMMENT_ANSWER) {
			Answer answer = answerService.getAnswerById(comment
					.getCommentedId());
			messageService.addMessageByRecerver(answer.getReplierId(),
					Message.MESSAGE_YOUR_ANSWER_COMMENTED, userId, userName,
					commentId, relatedSourceContent);
		}
	}
}
