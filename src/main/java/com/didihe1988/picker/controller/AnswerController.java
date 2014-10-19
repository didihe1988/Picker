package com.didihe1988.picker.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.form.AnswerForm;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.RelatedImageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.ImageUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private RelatedImageService relatedImageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/json/answer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getAnswer(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Answer answer = answerService.getAnswerById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_ANSWER,
				answer);
	}

	/**
	 * @description 删除该条回答
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String deleteAnswer(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = answerService.deleteAnswerById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 该回答下的评论
	 */
	@RequestMapping(value = "/json/answer/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getCommets(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_ANSWER);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description 用户赞了该评论
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String subscribe(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.incrementAnswerFavorite(id, userId);
		if (status == Status.SUCCESS) {
			produceSubscribeMessage(id, userId);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceSubscribeMessage(int answerId, int curUserId) {
		/*
		 * relatedId 设为questionId
		 */
		/*
		 * XXX赞了您的回答
		 */
		String curUserName = userService.getUserById(curUserId).getUsername();
		Answer answer = answerService.getAnswerById(answerId);
		String relatedSourceContent = StringUtils.confineStringLength(
				answer.getContent(), Constant.MESSAGE_LENGTH);
		String extraContent = feedService.getFeedById(answer.getQuestionId())
				.getTitle();
		messageService.addMessageByRecerver(answer.getReplierId(),
				Message.MESSAGE_YOUR_ANSWER_FAVORITED, curUserId, curUserName,
				answer.getQuestionId(), relatedSourceContent, extraContent,
				answer.getQuestionId());

		/*
		 * 通知关注者 小明 (被关注者)赞了XXX的
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_FAVORITE_ANSWER, curUserId,
				curUserName, answer.getQuestionId(), relatedSourceContent,
				extraContent, answer.getQuestionId());
		/*
		 * 通知关注该问题的人 xxx回答了该问题
		 */
		/*
		 * 用户动态
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_FAVORITE_ANSWER, curUserId, curUserName,
				answer.getQuestionId(), relatedSourceContent, extraContent,
				answer.getQuestionId());
	}

	@RequestMapping(value = "/json/answer/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decrementAnswerFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/answer/add", method = RequestMethod.POST)
	public @ResponseBody String add(@RequestBody Answer answer,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (!answer.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setAnswer(answer, request);
		int status = answerService.addAnswer(answer);
		if (status == Status.SUCCESS) {
			addAnserImage(answer);
			produceAnswerMessage(answer, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void setAnswer(Answer answer, HttpServletRequest request) {
		answer.setReplierId(HttpUtils.getSessionUserId(request));
		answer.setBriefByContent();
		answer.setDate(new Date());
	}

	@RequestMapping(value = "/json/answer/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody Answer answer,
			HttpServletRequest request) {
		/*
		 * 编辑回答
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int status = answerService.updateAnswer(answer, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceAnswerMessage(Answer answer, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = userService.getUserById(userId).getUsername();
		int askerId = feedService.getFeedById(answer.getQuestionId())
				.getUserId();

		// String userName = HttpUtils.getSessionUserName(request);
		/*
		 * answerId 需要查询获得
		 */
		int answerId = answerService.getLatestAnswerIdByQuestionId(answer
				.getQuestionId());

		String relatedSourceContent = StringUtils.confineStringLength(
				answer.getContent(), Constant.MESSAGE_LENGTH);
		String extraContent = feedService.getFeedById(answer.getQuestionId())
				.getTitle();
		/*
		 * 通知提问者XXX回答了您的问题
		 */
		messageService.addMessageByRecerver(askerId,
				Message.MESSAGE_YOUR_QUESTION_UPDATE, userId, userName,
				answerId, relatedSourceContent, extraContent,
				answer.getQuestionId());
		/*
		 * 通知关注该问题的人 问题有了新的回答
		 */
		messageService.addMessageByFollowedQuestion(
				Message.MESSAGE_QUESTION_NEWANSWER, userId, userName, answerId,
				relatedSourceContent, extraContent, answer.getQuestionId());
		/*
		 * 通知关注者 小明 (被关注者)回答了一个问题
		 */

		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ANSWER_QUESTION, userId, userName,
				answerId, relatedSourceContent, extraContent,
				answer.getQuestionId());
		/*
		 * 用户足迹
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDANSWER, userId, userName, answerId,
				relatedSourceContent, extraContent, answer.getQuestionId());
	}

	private void addAnserImage(Answer answer) {
		int answerId = answerService.getLatestAnswerIdByQuestionId(answer
				.getQuestionId());
		boolean isSuccess = ImageUtils.moveImage(RelatedImage.ANSWER_IMAGE,
				answerId, answer.getContent());
		/*
		 * file剪切出错或是没有imageUrl
		 */
		if (isSuccess) {
			addNewUrl(answer.getContent(), answerId);
		}
	}

	private void addNewUrl(String content, int answerId) {
		List<String> list = ImageUtils.getTmpUrlsFromContent(content);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * 替换content newUrl取代tmpUrl
			 */
			String newImageUrl = ImageUtils.getNewImageUrl(
					RelatedImage.ANSWER_IMAGE, answerId, i, list.get(i));
			content = content.replace(list.get(i), newImageUrl);
			/*
			 * 添加RelatedImage
			 */
			RelatedImage relatedImage = new RelatedImage(answerId,
					RelatedImage.ANSWER_IMAGE, newImageUrl);
			relatedImageService.addRelatedImage(relatedImage);
		}

	}

	/*
	 * **********************************************************
	 */
	@RequestMapping(value = "/answer/add/{questionId}", method = RequestMethod.POST)
	public String webAdd(@ModelAttribute AnswerForm answerForm,
			HttpServletRequest request,HttpServletResponse response) {
		if (!answerForm.checkFieldValidation()) {
			return "error";
		}
		Answer answer = new Answer(answerForm,
				HttpUtils.getSessionUserId(request));
		int status = answerService.addAnswer(answer);
		if (status == Status.SUCCESS) {
			//addAnserImage(answer);
			//produceAnswerMessage(answer, request);
		}
		try {
			response.sendRedirect("/detail/" + answer.getQuestionId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
}
