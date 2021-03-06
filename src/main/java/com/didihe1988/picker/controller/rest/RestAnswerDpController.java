package com.didihe1988.picker.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.display.AnswerDp;
import com.didihe1988.picker.model.display.CommentDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestAnswerDpController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/json/answer/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswerDp(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		AnswerDp answerDp = answerService.getAnswerDpByAnswerId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_ANSWER,
				answerDp);
	}

	/**
	 * @description 该回答下的评论
	 */
	@RequestMapping(value = "/json/answer/{id}/commentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommetDps(@PathVariable int id, HttpServletRequest request) {
		List<CommentDp> list = commentService.getCommentDpListByCommentedId(id,
				Comment.COMMENT_ANSWER, HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}
}
