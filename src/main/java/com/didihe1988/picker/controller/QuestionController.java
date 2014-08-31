package com.didihe1988.picker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.QuestionService;

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
	 */
	@RequestMapping(value = "/question/{id}")
	public String getQuestionDetail(@PathVariable int id, Model model) {
		QuestionDp questionDp = questionService.getQuestionDpByQuestionId(id);
		model.addAttribute("question", questionDp);
		model.addAttribute("answerList",
				answerService.getAnswerDpListByQuestionId(id));
		return "question";
	}
}
