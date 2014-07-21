package com.didihe1988.picker.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private MessageFactory messageFactory;

	@RequestMapping(value = "/question/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		String content = (String) request.getAttribute("content");
		Question question = new Question(bookId, userId, content, new Date());
		questionService.addQuestion(question);
		// 暂时只想出这种方法获得新Question的Id 可以专门写一个方法
		List<Question> questionList = questionService
				.getQuestionByAskerId(userId);
		messageFactory.addMessage(userId, userId,
				questionList.get(questionList.size() - 1).getId(),
				Message.MESSAGE_FOLLOWED_ASKQUESTION);
		return "";
	}

}
