package com.didihe1988.picker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.AnswerDp;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.CommentDp;
import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class QuestionDpController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/question/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionDp(@PathVariable int id) {
		QuestionDp questionDp = questionService.getQuestionDpByQuestionId(id);
		return JsonUtils.getJsonObjectString("question", questionDp);
	}

	/**
	 * @description 该问题下的回答
	 */
	@RequestMapping(value = "/question/{id}/answerdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id) {
		List<AnswerDp> list = answerService.getAnswerDpListByQuestionId(id);
		return JsonUtils.getJsonObjectString("answerList", list);
	}

	/**
	 * @description 该问题下的评论
	 */
	@RequestMapping(value = "/question/{id}/commentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<CommentDp> list = commentService.getCommentDpListByCommentedId(id,
				Comment.COMMENT_QUESTION);
		return JsonUtils.getJsonObjectString("commentList", list);
	}
}
