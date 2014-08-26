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
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class QuestionController {
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

	@RequestMapping(value = "/question/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Question getQuestion(@PathVariable int id) {
		Question question = questionService.getQuestionById(id);
		return question;
	}

	@RequestMapping(value = "/questiondp/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public QuestionDp getQuestionDp(@PathVariable int id) {
		return questionService.getQuestionDpByQuestionId(id);
	}

	/**
	 * @description �������µĻش�
	 */
	@RequestMapping(value = "/question/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Answer> getAnswers(@PathVariable int id) {
		return answerService.getAnswerByQuestionId(id);
	}

	/**
	 * @description �������µ�����
	 */
	@RequestMapping(value = "/question/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Comment> getCommets(@PathVariable int id) {
		return commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_QUESTION);
	}

	@RequestMapping(value = "/question/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		/*
		 * �������
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		String content = (String) request.getAttribute("content");
		String title = (String) request.getAttribute("title");
		Question question = new Question(bookId, userId, title, content);
		questionService.addQuestion(question);

		/*
		 * ֪ͨ��ע�� С�� (����ע��)�����һ������
		 */
		String userName = HttpUtils.getSessionUserName(request);
		int questionId = questionService.getLatestQuestionIdByBookId(bookId);
		String relatedSourceContent = StringUtils.confineStringLength(content,
				Constant.MESSAGE_LENGTH);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ASKQUESTION, userId, userName,
				questionId, relatedSourceContent);
		return "";
	}

	@RequestMapping(value = "/question/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		int questionId = HttpUtils.getIntegerFromReqeust(request, "questionId");
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementQuestionFavorite(questionId,
				userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX������������
			 */
			Question question = questionService.getQuestionById(questionId);
			String relatedSourceContent = StringUtils.confineStringLength(
					question.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(question.getAskerId(),
					Message.MESSAGE_YOUR_QUESTION_FAVORITED, userId, userName,
					questionId, relatedSourceContent);

			/*
			 * ֪ͨ��ע�� С�� (����ע��)����XXX������
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_QEUSTION, userId,
					userName, questionId, relatedSourceContent);
		}

		return "";
	}

}
