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
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.dp.CommentDp;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestQuestionDpController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/json/question/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionDp(@PathVariable int id, HttpServletRequest request) {
		/*
		 * id传-1时崩掉
		 */
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		FeedDp feedDp = feedService.getFeedDpByFeedId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_QUESTION,
				feedDp);
	}

	/**
	 * @description 该问题下的回答
	 */
	@RequestMapping(value = "/json/question/{id}/answerdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<AnswerDp> list = answerService.getAnswerDpListByQuestionId(id,
				HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 该问题下的评论
	 */
	@RequestMapping(value = "/json/question/{id}/commentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<CommentDp> list = commentService.getCommentDpListByCommentedId(id,
				Comment.COMMENT_QUESTION, HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}
}
