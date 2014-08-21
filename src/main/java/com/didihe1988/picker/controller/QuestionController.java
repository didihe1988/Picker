package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@RequestMapping(value = "/question/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		String content = (String) request.getAttribute("content");
		String title = (String) request.getAttribute("title");
		Question question = new Question(bookId, userId, title, content);
		questionService.addQuestion(question);
		// 暂时只想出这种方法获得新Question的Id 可以专门写一个方法
		List<Question> questionList = questionService
				.getQuestionByAskerId(userId);
		messageFactory.addMessage(userId, userId,
				questionList.get(questionList.size() - 1).getId(),
				Message.MESSAGE_FOLLOWED_ASKQUESTION);
		return "";
	}

	@RequestMapping(value = "/question/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		int questionId = HttpUtils.getIntegerFromReqeust(request, "questionId");
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementQuestionFavorite(questionId,
				userId);

		/*
		 * XXX赞了您的问题
		 */
		if (status == Status.SUCCESS) {
			Question question = questionService.getQuestionById(questionId);
			String relatedSourceContent = StringUtils.confineStringLength(
					question.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(question.getAskerId(),
					Message.MESSAGE_YOUR_QUESTION_FAVORITED, userId, userName,
					questionId, relatedSourceContent);
		}

		return "";
	}

}
