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
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.RelatedImage;
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
public class RestNoteController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private RelatedImageService relatedImageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/json/note/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNote(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Feed feed = feedService.getFeedById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_NOTE, feed);
	}

	/**
	 * @description �ñʼ��µ�����
	 */
	@RequestMapping(value = "/json/note/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description ɾ�������ʼ�
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/note/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteNote(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = feedService.deleteFeedById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description �û����˱ʼ�
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/json/note/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.incrementNoteFavorite(id, userId);
		if (status == Status.SUCCESS) {
			produceSubscribeMessage(id, userId);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceSubscribeMessage(int feedId, int curUserId) {
		/*
		 * XXX������������
		 */
		String curUserName = userService.getUserById(curUserId).getUsername();
		Feed feed = feedService.getFeedById(feedId);
		String relatedSourceContent = StringUtils.confineStringLength(
				feed.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * �������
		 */
		messageService.addMessageByRecerver(feed.getUserId(),
				Message.MESSAGE_YOUR_NOTE_FAVORITED, curUserId, curUserName,
				feedId, relatedSourceContent, feed.getBookId());
		/*
		 * ֪ͨ��ע�� С�� (����ע��)����XXX������
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_FAVORITE_NOTE, curUserId, curUserName,
				feedId, relatedSourceContent, feed.getBookId());
		/*
		 * �û���̬
		 */

		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_FAVORITE_NOTE, curUserId, curUserName,
				feedId, relatedSourceContent, feed.getBookId());
	}

	/**
	 * @description �û�ȡ���ĶԸñʼǵ���
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/note/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decrementNoteFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/note/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String add(@RequestBody Feed feed, HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (!feed.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setNote(feed, request);
		int status = feedService.addFeed(feed);
		if (status == Status.SUCCESS) {
			addNoteImage(feed);
			produceNoteMessage(feed, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void setNote(Feed feed, HttpServletRequest request) {
		feed.setType(Feed.TYPE_QUESTION);
		feed.setUserId(HttpUtils.getSessionUserId(request));
		feed.setBriefByContent();
		feed.setDate(new Date());
	}

	@RequestMapping(value = "/json/note/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public String update(@RequestBody Feed feed, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = feedService.updateFeed(feed, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceNoteMessage(Feed feed, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		// String userName = HttpUtils.getSessionUserName(request);
		String userName = userService.getUserById(userId).getUsername();
		String relatedSourceContent = StringUtils.confineStringLength(
				feed.getTitle(), Constant.MESSAGE_LENGTH);
		int noteId = feedService.getLatestFeedByBookId(feed.getBookId(),
				Feed.TYPE_NOTE);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ADDNOTE, userId, userName, noteId,
				relatedSourceContent, feed.getBookId());

		/*
		 * �û��㼣
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDNOTE, userId, userName, noteId,
				relatedSourceContent, feed.getBookId());
	}

	private void addNoteImage(Feed feed) {
		int noteId = feedService.getLatestFeedByBookId(feed.getBookId(),
				Feed.TYPE_NOTE);
		boolean isSuccess = ImageUtils.moveImage(RelatedImage.NOTE_IMAGE,
				noteId, feed.getContent());
		/*
		 * file���г������û��imageUrl
		 */
		if (isSuccess) {
			addNewUrl(feed.getContent(), noteId);
		}
	}

	private void addNewUrl(String content, int noteId) {
		List<String> list = ImageUtils.getTmpUrlsFromContent(content);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * �滻content newUrlȡ��tmpUrl
			 */
			String newImageUrl = ImageUtils.getNewImageUrl(
					RelatedImage.NOTE_IMAGE, noteId, i, list.get(i));
			content = content.replace(list.get(i), newImageUrl);
			/*
			 * ���RelatedImage
			 */
			RelatedImage relatedImage = new RelatedImage(noteId,
					RelatedImage.NOTE_IMAGE, newImageUrl);
			relatedImageService.addRelatedImage(relatedImage);
		}

	}
}
