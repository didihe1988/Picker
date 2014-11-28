package com.didihe1988.picker.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.controller.interfaces.FavoriteController;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.model.form.FeedForm;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.RelatedImageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.ImageUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RestQuestionController implements FavoriteController {

	@Autowired
	private FeedService feedService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FollowService followService;

	@Autowired
	private UserService userService;

	@Autowired
	private RelatedImageService relatedImageService;

	@Override
	public int getFavoriteType() {
		// TODO Auto-generated method stub
		return Favorite.FAVORITE_QUESTION;
	}

	@RequestMapping(value = "/json/question/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestion(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Feed feed = feedService.getFeedById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_QUESTION,
				feed);
	}

	/**
	 * @description 该问题下的回答
	 */
	@RequestMapping(value = "/json/question/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAnswers(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Answer> list = answerService.getAnswerListByQuestionId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_ANSWER_LIST, list);
	}

	/**
	 * @description 该问题下的评论
	 */
	@RequestMapping(value = "/json/question/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_QUESTION);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description 删除该条问题
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/question/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteQuestion(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = feedService.deleteFeedById(id, userId);
		return JsonUtils.getJsonObjectString("status", status);
	}

	/**
	 * @description 获得关注该问题的用户列表
	 */
	@RequestMapping(value = "/json/question/{id}/followers", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFollowers(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Follow> followList = followService.getFollowListByQuestionId(id);
		List<UserDp> list = new ArrayList<UserDp>();
		for (Follow follow : followList) {
			UserDp userDp = userService
					.getUserDpByUserId(follow.getFollowerId(),
							HttpUtils.getSessionUserId(request));
			list.add(userDp);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_USER_LIST, list);
	}

	/**
	 * @description 关注该问题
	 */
	@RequestMapping(value = "/json/question/{id}/follow", method = RequestMethod.GET)
	public String follow(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		Follow follow = new Follow(Follow.FOLLOW_QUESTION, userId, id);
		int status = followService.addFollow(follow);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 取消关注该问题
	 */
	/*
	 * 这个不知道行不行
	 */
	@RequestMapping(value = "/json/question/{id}/withdraw_follow", method = RequestMethod.GET)
	public String withdrawFollow(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = followService.deleteFollow(Follow.FOLLOW_QUESTION, userId,
				id);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户赞了该问题
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/json/question/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.incModelFavorite(id, userId,
				getFavoriteType());
		if (status == Status.SUCCESS) {
			produceSubscribeMessage(id, userId);
		}
		int favoriteNum = feedService.getFeedById(id).getFavoriteNum();
		return JsonUtils.getJsonObjectString(Constant.KEY_FAVORITENUM,
				favoriteNum);
	}

	/**
	 * @description 用户取消了赞
	 * @condition session-userId
	 */
	@RequestMapping(value = "/json/question/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decModelFavorite(id, userId,
				getFavoriteType());
		// return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
		int favoriteNum = feedService.getFeedById(id).getFavoriteNum();
		return JsonUtils.getJsonObjectString(Constant.KEY_FAVORITENUM,
				favoriteNum);
	}

	@RequestMapping(value = "/json/question/add", method = RequestMethod.POST)
	public String add(@RequestBody Feed feed, HttpServletRequest request) {
		if (!feed.checkFieldValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID_FIELD);
		}
		setQuestion(feed, request);
		int status = feedService.addFeed(feed);
		if (status == Status.SUCCESS) {
			produceQuestionMessage(feed, request);
			addQuestionImage(feed);
		}

		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void setQuestion(Feed feed, HttpServletRequest request) {
		feed.setType(Feed.TYPE_QUESTION);
		feed.setUserId(HttpUtils.getSessionUserId(request));
		feed.setBriefByContent();
		feed.setDate(new Date());
	}

	@RequestMapping(value = "/json/question/update", method = RequestMethod.POST)
	public String update(@RequestBody Feed feed, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		// int status = questionService.updateQuestion(question, userId);
		int status = feedService.updateFeed(feed, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void produceQuestionMessage(Feed feed, HttpServletRequest request) {
		/*
		 * 通知关注者 小明 (被关注者)提出了一个问题
		 */
		int userId = HttpUtils.getSessionUserId(request);
		String userName = userService.getUserById(userId).getUsername();
		int feedId = feedService.getLatestFeedByBookId(feed.getBookId(),
				Feed.TYPE_QUESTION);
		String relatedSourceContent = StringUtils.confineStringLength(
				feed.getBrief(), Constant.MESSAGE_LENGTH);
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ASKQUESTION, userId, userName, feedId,
				relatedSourceContent, feed.getTitle(), feed.getBookId());
		/*
		 * 用户足迹
		 */
		messageService.addMessageByRecerver(Message.NULL_receiverId,
				Message.MESSAGE_USER_ADDQUESTION, userId, userName, feedId,
				relatedSourceContent, feed.getTitle(), feed.getBookId());
	}

	private void produceSubscribeMessage(int feedId, int curUserId) {
		/*
		 * XXX赞了您的问题
		 */
		String curUserName = userService.getUserById(curUserId).getUsername();
		Feed feed = feedService.getFeedById(feedId);
		String relatedSourceContent = StringUtils.confineStringLength(
				feed.getContent(), Constant.MESSAGE_LENGTH);
		/*
		 * 与我相关
		 */
		messageService.addMessageByRecerver(feed.getUserId(),
				Message.MESSAGE_YOUR_QUESTION_FAVORITED, curUserId,
				curUserName, feedId, relatedSourceContent, feed.getTitle(),
				feed.getBookId());
		/*
		 * 通知关注者 小明 (被关注者)赞了XXX的问题
		 */
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_FAVORITE_QEUSTION, curUserId,
				curUserName, feedId, relatedSourceContent, feed.getTitle(),
				feed.getBookId());
		/*
		 * 用户动态
		 */

		messageService
				.addMessageByRecerver(Message.NULL_receiverId,
						Message.MESSAGE_USER_FAVORITE_QUESTION, curUserId,
						curUserName, feedId, relatedSourceContent,
						feed.getTitle(), feed.getBookId());

	}

	private void addQuestionImage(Feed feed) {
		int questionId = feedService.getLatestFeedByBookId(feed.getBookId(),
				Feed.TYPE_QUESTION);
		boolean isSuccess = ImageUtils.moveImage(RelatedImage.FEED_IMAGE,
				questionId, feed.getContent());
		/*
		 * false情况: file剪切出错或是没有imageUrl
		 */
		if (isSuccess) {
			addNewUrl(feed.getContent(), questionId);
		}
	}

	private void addNewUrl(String content, int questionId) {
		List<String> list = ImageUtils.getTmpUrlsFromContent(content);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * 替换content newUrl取代tmpUrl
			 */
			String newImageUrl = ImageUtils.getNewImageUrl(
					RelatedImage.FEED_IMAGE, questionId, i, list.get(i));
			content = content.replace(list.get(i), newImageUrl);
			/*
			 * 添加RelatedImage
			 */
			RelatedImage relatedImage = new RelatedImage(questionId,
					RelatedImage.FEED_IMAGE, newImageUrl);
			relatedImageService.addRelatedImage(relatedImage);
		}
	}

}
