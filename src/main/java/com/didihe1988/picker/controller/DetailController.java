package com.didihe1988.picker.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.display.UserDp;
import com.didihe1988.picker.model.form.FeedForm;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.AttachmentService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.RelatedImageService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.ImageUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class DetailController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private RelatedImageService relatedImageService;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/detail/{id}")
	public String question(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if ((id < 1) || (!HttpUtils.isSessionUserIdExists(request))) {
			return "error";
		}
		if (feedService.isFeedExistsById(id)) {
			FeedDp feedDp = feedService.getFeedDpByFeedId(id,
					HttpUtils.getSessionUserId(request));
			UserDp userDp = userService.getUserDpByUserId(feedDp.getUserId(),
					HttpUtils.getSessionUserId(request));
			model.addAttribute("user", userDp);
			model.addAttribute("question", feedDp);
			model.addAttribute(
					"answerList",
					answerService.getAnswerDpListByQuestionId(id,
							HttpUtils.getSessionUserId(request)));
			return "detail";
		} else {
			return "error";
		}

	}

	// http://localhost:5000/detail/1234/15/new

	@RequestMapping(value = "/detail/{bookId}/{page}/new")
	public String newFeed(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			Book book = bookService.getBookById(bookId);
			model.addAttribute("book", book);
			model.addAttribute("page", page);
			return "new";
		} else {
			return "error";
		}

	}

	// localhost:5000/detail/112/12/create
	@RequestMapping(value = "/detail/{bookId}/{page}/create")
	public String createFeed(@PathVariable int bookId,
			@ModelAttribute FeedForm feedForm, HttpServletRequest request,
			HttpServletResponse response) {

		if ((bookId < 1) || (feedForm.getPage() < 0)
				|| (!feedForm.checkValidation())) {
			return "error";
		}
		feedForm.setTitle(StringUtils.toUTF8(feedForm.getTitle()));
		feedForm.setRaw(StringUtils.toUTF8(feedForm.getRaw()));
		Feed feed = new Feed(feedForm, bookId,
				HttpUtils.getSessionUserId(request));
		int status = feedService.addFeed(feed);

		if (status == Status.SUCCESS) {
			// 一会儿再改、添加附件的消息
			// addFeedMessage(feed, request);
			// addFeedImage(feed);
		}
		if (status == Status.SUCCESS) {
			if(feedForm.getType().equals("attachment"))
			{
				bindAttachment(toAttachmentIdList(feedForm.getAttachmentIds()),
						bookId, feedService.getLatestFeedByBookId(bookId,
								Feed.TYPE_ATTACHMENT_FEED));
			}
		
		}
		try {
			response.sendRedirect("/browse/" + bookId + "/feeds/1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}

	private List<Integer> toAttachmentIdList(String attachmentIds) {
		List<Integer> list = new ArrayList<Integer>();
		String[] ids = attachmentIds.split("\\.");
		for (String id : ids) {
			try {
				list.add(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	private void bindAttachment(List<Integer> attachmentIds, int bookId,
			int attFeedId) {
		for (int id : attachmentIds) {
			Attachment attachment = attachmentService.getAttachmentById(id);
			if (attachment == null) {
				// 需要报错还是怎样？
				break;
			}
			// uplate
			attachment.setaFeedId(attFeedId);
			attachment.setPath("/resources/attachment/book/" + bookId + "/"
					+ attachment.getName());
			attachmentService.updateAttachment(attachment);
			// move file
			moveAttachmentFile(bookId, attachment.getName());

		}
	}

	private void moveAttachmentFile(int bookId, String fileName) {
		String dirString = Constant.ROOTDIR + "attachment/book/" + bookId + "/";
		File dir = new File(dirString);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File oldFile = new File(Constant.ROOTDIR + "tmp/attachment/" + fileName);
		if (!oldFile.exists()) {
			return;
		}
		try {
			oldFile.renameTo(new File(dirString + fileName));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void addFeedMessage(Feed feed, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		User producer = userService.getUserById(userId);
		int feedId = feedService.getLatestFeedByBookId(feed.getBookId(),
				feed.getType());
		String relatedSourceContent = StringUtils.confineStringLength(
				feed.getBrief(), Constant.MESSAGE_LENGTH);
		String extraContent = feedService.getFeedById(feedId).getTitle();
		if (feed.getType() == Feed.TYPE_QUESTION) {
			/*
			 * messageService.addMessageByFollowedUser(true,
			 * DynamicFilter.getTypeCode(),
			 * DynamicFilter.MESSAGE_FOLLOWED_ASKQUESTION, producer, feedId,
			 * relatedSourceContent, feed.getTitle(), feed.getBookId()); //用户足迹
			 * messageService.addMessageByRecerver(Message.NULL_receiverId,
			 * true, FootprintFilter.getTypeCode(),
			 * FootprintFilter.MESSAGE_USER_ADDQUESTION, producer, feedId,
			 * relatedSourceContent, feed.getTitle(), feed.getBookId());
			 */
			messageService.addMessageByFollowedUser(true,
					Message.MESSAGE_FOLLOWED_ASKQUESTION, producer, feedId,
					relatedSourceContent, feed.getTitle(), feed.getBookId());
			// 用户足迹
			messageService.addMessageByRecerver(Message.NULL_receiverId, true,
					Message.MESSAGE_USER_ADDQUESTION, producer, feedId,
					relatedSourceContent, feed.getTitle(), feed.getBookId());
		} else {
			/*
			 * messageService.addMessageByFollowedUser(true,
			 * DynamicFilter.getTypeCode(),
			 * DynamicFilter.MESSAGE_FOLLOWED_ADDNOTE, producer, feedId,
			 * relatedSourceContent, extraContent, feed.getBookId()); //用户足迹
			 * messageService.addMessageByRecerver(Message.NULL_receiverId,
			 * true, FootprintFilter.getTypeCode(),
			 * FootprintFilter.MESSAGE_USER_ADDNOTE, producer, feedId,
			 * relatedSourceContent, extraContent, feed.getBookId());
			 */
			messageService.addMessageByFollowedUser(true,
					Message.MESSAGE_FOLLOWED_ADDNOTE, producer, feedId,
					relatedSourceContent, extraContent, feed.getBookId());
			// 用户足迹
			messageService.addMessageByRecerver(Message.NULL_receiverId, true,
					Message.MESSAGE_USER_ADDNOTE, producer, feedId,
					relatedSourceContent, extraContent, feed.getBookId());
		}
	}

	private void addFeedImage(Feed feed) {
		int feedId;
		if (feed.getType() == Feed.TYPE_QUESTION) {
			feedId = feedService.getLatestFeedByBookId(feed.getBookId(),
					Feed.TYPE_QUESTION);
		} else {
			feedId = feedService.getLatestFeedByBookId(feed.getBookId(),
					Feed.TYPE_NOTE);
		}
		boolean isSuccess = ImageUtils.moveImage(RelatedImage.FEED_IMAGE,
				feedId, feed.getContent());
		/*
		 * false情况: file剪切出错或是没有imageUrl
		 */
		if (isSuccess) {
			addNewUrl(feed.getContent(), feedId);
		}
	}

	private void addNewUrl(String content, int feedId) {
		List<String> list = ImageUtils.getTmpUrlsFromContent(content);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * 替换content newUrl取代tmpUrl
			 */
			String newImageUrl = ImageUtils.getNewImageUrl(
					RelatedImage.FEED_IMAGE, feedId, i, list.get(i));
			content = content.replace(list.get(i), newImageUrl);
			/*
			 * 添加RelatedImage
			 */
			RelatedImage relatedImage = new RelatedImage(feedId,
					RelatedImage.FEED_IMAGE, newImageUrl);
			relatedImageService.addRelatedImage(relatedImage);
		}
	}

}
