package com.didihe1988.picker.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.controller.interfaces.FavoriteController;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.form.CommentForm;
import com.didihe1988.picker.model.message.DynamicFilter;
import com.didihe1988.picker.model.message.FootprintFilter;
import com.didihe1988.picker.model.message.SelfRelatedFilter;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RestCommentController implements FavoriteController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	@Override
	public int getFavoriteType() {
		// TODO Auto-generated method stub
		return Favorite.FAVORITE_COMMENT;
	}

	@RequestMapping(value = "/json/comment/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getComment(@PathVariable int id) {
		Comment comment = commentService.getCommentById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_COMMENT,
				comment);
	}

	@RequestMapping(value = "/json/comment/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addComment(@RequestBody CommentForm commentForm,
			HttpServletRequest request) {
		if (!commentForm.checkFieldValidation()) {
			return Constant.STATUS_INVALID;
		}
		Comment comment = Comment.getComment(commentForm,
				HttpUtils.getSessionUserId(request));
		int status = commentService.addComment(comment);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 删除该条评论
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/comment/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteComment(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = commentService.deleteCommentById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户赞了该评论
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/json/comment/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		int userId = HttpUtils.getSessionUserId(request);
		// String userName = HttpUtils.getSessionUserName(request);
		User producer = userService.getUserById(userId);
		int status = favoriteService.incModelFavorite(id, userId,
				getFavoriteType());
		System.out.println(status);
		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的评论 两个函数都查询了comemnt 有待优化
			 */
			/*
			 * extraContent暂时为空
			 */
			Comment comment = commentService.getCommentById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					comment.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(comment.getProducerId(), false,
					SelfRelatedFilter.getTypeCode(),
					SelfRelatedFilter.MESSAGE_YOUR_COMMENT_FAVORITED, producer,
					id, relatedSourceContent, Message.NULL_ExtraContent,
					Message.NULL_parentId);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的评论
			 */
			messageService.addMessageByFollowedUser(false,
					DynamicFilter.getTypeCode(),
					DynamicFilter.MESSAGE_FOLLOWED_FAVORITE_COMMENT, producer,
					id, relatedSourceContent, Message.NULL_ExtraContent,
					Message.NULL_parentId);

			/*
			 * 用户动态
			 */
			messageService.addMessageByRecerver(Message.NULL_receiverId, false,
					FootprintFilter.getTypeCode(),
					FootprintFilter.MESSAGE_USER_FAVORITE_COMMENT, producer,
					id, relatedSourceContent, Message.NULL_ExtraContent,
					Message.NULL_parentId);
		}

		// return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
		int favoriteNum = commentService.getCommentById(id).getFavoriteNum();
		return JsonUtils.getJsonObjectString(Constant.KEY_FAVORITENUM,
				favoriteNum);
	}

	/**
	 * @description 用户取消了赞
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/comment/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		// int userId = HttpUtils.getSessionUser(request).getId();
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decModelFavorite(id, userId,
				getFavoriteType());
		// return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
		int favoriteNum = commentService.getCommentById(id).getFavoriteNum();
		return JsonUtils.getJsonObjectString(Constant.KEY_FAVORITENUM,
				favoriteNum);
	}

	@RequestMapping(value = "/json/comment", method = RequestMethod.POST)
	public String add(@RequestBody Comment comment, HttpServletRequest request) {
		/*
		 * 添加评论
		 */
		int status = commentService.addComment(comment);
		if (status == Status.SUCCESS) {
			addCommentMessage(comment, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addCommentMessage(Comment comment, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int commentId = commentService.getLatestCommentIdByUserId(userId);
		// 评论时的producer就是当前user
		User producer = userService.getUserById(userId);
		// relatedSourceContent-评论内容
		String relatedSourceContent = StringUtils.confineStringLength(
				comment.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * 小明 评论了您的问题
		 */
		if (comment.getType() == Comment.COMMENT_QUESTION) {
			Feed feed = feedService.getFeedById(comment.getCommentedId());
			messageService.addMessageByRecerver(feed.getUserId(), false,
					SelfRelatedFilter.getTypeCode(),
					SelfRelatedFilter.MESSAGE_YOUR_QUESTION_COMMENTED,
					producer, commentId, relatedSourceContent,
					Message.NULL_ExtraContent, Message.NULL_parentId);
		}

		/*
		 * 小明 评论了您的回答
		 */
		if (comment.getType() == Comment.COMMENT_ANSWER) {
			Answer answer = answerService.getAnswerById(comment
					.getCommentedId());
			messageService.addMessageByRecerver(answer.getReplierId(), false,
					SelfRelatedFilter.getTypeCode(),
					SelfRelatedFilter.MESSAGE_YOUR_ANSWER_COMMENTED, producer,
					commentId, relatedSourceContent, Message.NULL_ExtraContent,
					Message.NULL_parentId);
		}

		/*
		 * 用户动态
		 */
		/*
		 * messageService.addMessageByRecerver(Message.NULL_receiverId,
		 * Message.MESSAGE_USER_ADDCOMMENT, userId, userName, commentId,
		 * relatedSourceContent);
		 */
	}
}
