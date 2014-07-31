package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private MessageFactory messageFactory;

	@RequestMapping(value = "/answer/add.do")
	public String add(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int questionId = HttpUtils.getIntegerFromReqeust(request, "questionId");
		String content = HttpUtils.getStringFromReqeust(request, "content");
		Answer answer = new Answer(questionId, content);
		answerService.addAnswer(answer);
		int answerId = answerService.getLatestAnswerIdByQuestionId(questionId);
		messageFactory.addMessage(userId, userId, answerId,
				Message.MESSAGE_FOLLOWED_ANSWER_QUESTION);
		return "";
	}
}
