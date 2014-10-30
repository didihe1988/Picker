package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.CommentDp;
import com.didihe1988.picker.model.json.CommentJson;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FeedService feedService;

	@RequestMapping(value = "/note/{id}/comments")
	public @ResponseBody String noteComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_NOTE,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	@RequestMapping(value = "/question/{id}/comments")
	public @ResponseBody String questionComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_QUESTION,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	@RequestMapping(value = "/feed/{id}/comments")
	public @ResponseBody String feedComments(@PathVariable int id,
			HttpServletRequest request) {
		Feed feed = feedService.getFeedById(id);
		if (feed == null) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		if (feed.getType() == Feed.TYPE_QUESTION) {
			return questionComments(id, request);
		} else {
			return noteComments(id, request);
		}
	}

	@RequestMapping(value = "/answer/{id}/comments")
	public @ResponseBody String answerComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_ANSWER,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	private String getJsonString(List<CommentDp> commentDps) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comments", getCommentJsons(commentDps));
		jsonObject.put("total", commentDps.size());
		jsonObject.put("status", "success");
		return jsonObject.toString();
	}

	private List<CommentJson> getCommentJsons(List<CommentDp> commentDps) {
		List<CommentJson> commentJsons = new ArrayList<CommentJson>();
		for (CommentDp commentDp : commentDps) {
			commentJsons.add(getCommentJson(commentDp));
		}
		return commentJsons;
	}

	private CommentJson getCommentJson(CommentDp commentDp) {
		return new CommentJson(commentDp.getProducerId(),
				commentDp.getProducerName(), commentDp.getContent(),
				commentDp.getUserAvatarUrl(), commentDp.getStrDate());
	}

}
