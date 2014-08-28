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
	 * @description ɾ����������
	 * @condition session-userId
	 */
	@RequestMapping(value = "/comment/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteComment(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = commentService.deleteCommentById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description �û����˸�����
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/comment/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementCommentFavorite(id, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX������������ ������������ѯ��comemnt �д��Ż�
			 */
			Comment comment = commentService.getCommentById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					comment.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(comment.getProducerId(),
					Message.MESSAGE_YOUR_COMMENT_FAVORITED, userId, userName,
					id, relatedSourceContent);

			/*
			 * ֪ͨ��ע�� С�� (����ע��)����XXX������
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_COMMENT, userId,
					userName, id, relatedSourceContent);
		}

		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description �û�ȡ������
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
		 * �������
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
		// relatedSourceContent-��������
		String relatedSourceContent = StringUtils.confineStringLength(
				comment.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * С�� ��������������
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
		 * С�� ���������Ļش�
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
