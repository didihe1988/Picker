package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	/**
	 * @description question.jsp
	 * @consition request userId
	 */
	@RequestMapping(value = "/question/{id}")
	public String getQuestionDetail(@PathVariable int id, Model model,HttpServletRequest request) {
		QuestionDp questionDp = questionService.getQuestionDpByQuestionId(id,
				HttpUtils.getSessionUserId(request));
		model.addAttribute("question", questionDp);
		model.addAttribute("answerList",
				answerService.getAnswerDpListByQuestionId(id,
						HttpUtils.getSessionUserId(request)));
		return "question";
	}
}
