package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.CommentDp;
import com.didihe1988.picker.model.form.CommentForm;
import com.didihe1988.picker.model.json.CommentJson;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.Entity;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private FeedService feedService;

	@RequestMapping(value = "/note/{id}/comments", produces = "application/json")
	public @ResponseBody String noteComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_NOTE,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	@RequestMapping(value = "/question/{id}/comments", produces = "application/json")
	public @ResponseBody String questionComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_QUESTION,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	@RequestMapping(value = "/feed/{id}/comments", produces = "application/json")
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

	@RequestMapping(value = "/answer/{id}/comments", produces = "application/json")
	public @ResponseBody String answerComments(@PathVariable int id,
			HttpServletRequest request) {
		List<CommentDp> commentDps = commentService
				.getCommentDpListByCommentedId(id, Comment.COMMENT_ANSWER,
						HttpUtils.getSessionUserId(request));
		return getJsonString(commentDps);
	}

	/*
	 * @RequestMapping(value = "/comment/add", produces = "application/json")
	 * public @ResponseBody String addComment(
	 * 
	 * @ModelAttribute CommentForm commentForm, HttpServletRequest request) { if
	 * (!commentForm.checkFieldValidation()) {
	 * JsonUtils.getJsonObjectString(Constant.KEY_STATUS, Status.INVALID); }
	 * Comment comment = Comment.getComment(commentForm,
	 * HttpUtils.getSessionUserId(request)); int status =
	 * commentService.addComment(comment); return
	 * JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status); }
	 */

	@RequestMapping(value = "/answer/comment/add", produces = "application/json")
	public @ResponseBody String addAnswerComment(
			@ModelAttribute CommentForm commentForm, HttpServletRequest request) {
		if (!commentForm.checkFieldValidationWithoutType()) {
			JsonUtils.getJsonObjectString(Constant.KEY_STATUS, Status.INVALID);
		}
		commentForm.setType(Comment.COMMENT_ANSWER);
		Comment comment = Comment.getComment(commentForm,
				HttpUtils.getSessionUserId(request));
		int status = commentService.addComment(comment);
		int commentNum = answerService.getAnswerById(comment.getCommentedId())
				.getCommentNum();
		return JsonUtils.getJsonObjectString(new Entity(Constant.KEY_STATUS,
				status), new Entity(Constant.KEY_COMMENTNUM, commentNum));
	}

	@RequestMapping(value = "/question/comment/add", produces = "application/json")
	public @ResponseBody String addQuestionComment(
			@ModelAttribute CommentForm commentForm, HttpServletRequest request) {
		if (!commentForm.checkFieldValidationWithoutType()) {
			JsonUtils.getJsonObjectString(Constant.KEY_STATUS, Status.INVALID);
		}
		commentForm.setType(Comment.COMMENT_QUESTION);
		Comment comment = Comment.getComment(commentForm,
				HttpUtils.getSessionUserId(request));
		int status = commentService.addComment(comment);
		int commentNum = feedService.getFeedById(comment.getCommentedId())
				.getCommentNum();
		return JsonUtils.getJsonObjectString(new Entity(Constant.KEY_STATUS,
				status), new Entity(Constant.KEY_COMMENTNUM, commentNum));
	}

	@RequestMapping(value = "/note/comment/add", produces = "application/json")
	public @ResponseBody String addNoteComment(
			@ModelAttribute CommentForm commentForm, HttpServletRequest request) {
		if (!commentForm.checkFieldValidationWithoutType()) {
			JsonUtils.getJsonObjectString(Constant.KEY_STATUS, Status.INVALID);
		}
		commentForm.setType(Comment.COMMENT_NOTE);
		Comment comment = Comment.getComment(commentForm,
				HttpUtils.getSessionUserId(request));
		int status = commentService.addComment(comment);
		int commentNum = feedService.getFeedById(comment.getCommentedId())
				.getCommentNum();
		return JsonUtils.getJsonObjectString(new Entity(Constant.KEY_STATUS,
				status), new Entity(Constant.KEY_COMMENTNUM, commentNum));
	}

	/*
	 * private void setFeedCommentType(CommentForm commentForm, int feedType) {
	 * if (feedType == Feed.TYPE_QUESTION) {
	 * commentForm.setType(Comment.COMMENT_QUESTION); }
	 * commentForm.setType(Comment.COMMENT_NOTE); }
	 */

	private String getJsonString(List<CommentDp> commentDps) {
		return JsonUtils.getJsonObjectString(new Entity("comments",
				getCommentJsons(commentDps)),
				new Entity("total", commentDps.size()), new Entity("status",
						"success"));
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
