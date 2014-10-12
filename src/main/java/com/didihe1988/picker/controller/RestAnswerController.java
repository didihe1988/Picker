package com.didihe1988.picker.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.RelatedImage;
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

@RestController
public class RestAnswerController {
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
	public String getAnswer(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Answer answer = answerService.getAnswerById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_ANSWER,
				answer);
	}

	/**
	 * @description ɾ�������ش�
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteAnswer(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = answerService.deleteAnswerById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description �ûش��µ�����
	 */
	@RequestMapping(value = "/json/answer/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_ANSWER);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description �û����˸�����
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/answer/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
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
		 * relatedId ��ΪquestionId
		 */
		/*
		 * XXX�������Ļش�
		 */
		String curUserName = userService.getUserById(curUserId).getUsername();
		Answer answer = answerService.getAnswerById(answerId);
		String relatedSourceContent = StringUtils.confineStringLength(
				answer.getContent(), Constant.MESSAGE_LENGTH);
		messageService.addMessageByRecerver(answer.getReplierId(),
				Message.MESSAGE_YOUR_ANSWER_FAVORITED, curUserId, curUserName,
				answer.getQuestionId(), relatedSourceContent,
				answer.getQuestionId());

		/*
		 * ֪ͨ��ע�� С�� (����ע��)����XXX������
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_FAVORITE_ANSWER, curUserId,
				curUserName, answer.getQuestionId(), relatedSourceContent,
				answer.getQuestionId());
		/*
		 * �û���̬
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_FAVORITE_ANSWER, curUserId, curUserName,
				answer.getQuestionId(), relatedSourceContent,
				answer.getQuestionId());
	}

	@RequestMapping(value = "/json/answer/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
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
	public String add(@RequestBody Answer answer, HttpServletRequest request) {
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
	public String update(@RequestBody Answer answer, HttpServletRequest request) {
		/*
		 * �༭�ش�
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
		 * answerId ��Ҫ��ѯ���
		 */
		int answerId = answerService.getLatestAnswerIdByQuestionId(answer
				.getQuestionId());

		String relatedSourceContent = StringUtils.confineStringLength(
				answer.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * ֪ͨ������XXX�ش�����������
		 */
		messageService.addMessageByRecerver(askerId,
				Message.MESSAGE_YOUR_QUESTION_UPDATE, userId, userName,
				answerId, relatedSourceContent, answer.getQuestionId());
		/*
		 * ֪ͨ��ע�� С�� (����ע��)�ش���һ������
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ANSWER_QUESTION, userId, userName,
				answerId, relatedSourceContent, answer.getQuestionId());
		/*
		 * �û��㼣
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDANSWER, userId, userName, answerId,
				relatedSourceContent, answer.getQuestionId());
	}

	private void addAnserImage(Answer answer) {
		int answerId = answerService.getLatestAnswerIdByQuestionId(answer
				.getQuestionId());
		boolean isSuccess = ImageUtils.moveImage(RelatedImage.ANSWER_IMAGE,
				answerId, answer.getContent());
		/*
		 * file���г������û��imageUrl
		 */
		if (isSuccess) {
			addNewUrl(answer.getContent(), answerId);
		}
	}

	private void addNewUrl(String content, int answerId) {
		List<String> list = ImageUtils.getTmpUrlsFromContent(content);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * �滻content newUrlȡ��tmpUrl
			 */
			String newImageUrl = ImageUtils.getNewImageUrl(
					RelatedImage.ANSWER_IMAGE, answerId, i, list.get(i));
			content = content.replace(list.get(i), newImageUrl);
			/*
			 * ���RelatedImage
			 */
			RelatedImage relatedImage = new RelatedImage(answerId,
					RelatedImage.ANSWER_IMAGE, newImageUrl);
			relatedImageService.addRelatedImage(relatedImage);
		}

	}

}
