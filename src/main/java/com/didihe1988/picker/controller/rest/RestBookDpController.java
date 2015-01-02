package com.didihe1988.picker.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.DaoConstant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.impl.FeedDaoImpl;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.service.AttachmentFeedService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestBookDpController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private AttachmentFeedService attachmentFeedService;

	/**
	 * @description 本书下提出的问题
	 * @condition request userId
	 */
	@RequestMapping(value = "/json/book/{id}/questiondps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionDps(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<FeedDp> list = new ArrayList<FeedDp>();
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("page"))) {
			list = feedService.getFeedDpListByBookId(id, Feed.TYPE_QUESTION,
					HttpUtils.getSessionUserId(request),
					DaoConstant.FeedPageOrder);
		} else {
			list = feedService.getFeedDpListByBookId(id, Feed.TYPE_QUESTION,
					HttpUtils.getSessionUserId(request));
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	@RequestMapping(value = "/json/book/{id}/questiondps/{limit}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getLimitedQuestionDps(@PathVariable int id,
			@PathVariable int limit, HttpServletRequest request) {
		if ((id < 1) || (limit < 0)) {
			return Constant.STATUS_INVALID;
		}
		String type = (String) request.getParameter("type");
		List<FeedDp> list = new ArrayList<FeedDp>();
		if ((type != null) && (type.equals("page"))) {
			list = feedService.getLimitedFeedDpListByBookId(id,
					Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request),
					limit, DaoConstant.FeedPageOrder);
		} else {
			list = feedService.getLimitedFeedDpListByBookId(id,
					Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request),
					limit);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/json/book/{id}/notedps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNoteDps(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<FeedDp> list = new ArrayList<FeedDp>();
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("page"))) {
			list = feedService.getFeedDpListByBookId(id, Feed.TYPE_NOTE,
					HttpUtils.getSessionUserId(request),
					DaoConstant.FeedPageOrder);
		} else {
			list = feedService.getFeedDpListByBookId(id, Feed.TYPE_NOTE,
					HttpUtils.getSessionUserId(request));
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	@RequestMapping(value = "/json/book/{id}/notedps/{limit}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getLimitedNoteDps(@PathVariable int id,
			@PathVariable int limit, HttpServletRequest request) {
		if ((id < 1) || (limit < 0)) {
			return Constant.STATUS_INVALID;
		}
		List<FeedDp> list = new ArrayList<FeedDp>();
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("page"))) {
			list = feedService.getLimitedFeedDpListByBookId(id, Feed.TYPE_NOTE,
					HttpUtils.getSessionUserId(request), limit,
					DaoConstant.FeedPageOrder);
		} else {
			list = feedService.getLimitedFeedDpListByBookId(id, Feed.TYPE_NOTE,
					HttpUtils.getSessionUserId(request), limit);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 本书附件帖
	 */
	@RequestMapping(value = "/json/book/{id}/attachmentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAttachments(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<AttachmentFeedDp> list = new ArrayList<AttachmentFeedDp>();
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("page"))) {
			list = attachmentFeedService.getAttachmentFeedDpsByBookId(id,
					DaoConstant.AfeedPageOrder);
		} else {
			list = attachmentFeedService.getAttachmentFeedDpsByBookId(id);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_ATTACHMENTFEED_LIST,
				list);
	}

	@RequestMapping(value = "/json/book/{id}/attachmentdps/{limit}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getLimitedAttachments(@PathVariable int id,
			@PathVariable int limit, HttpServletRequest request) {
		if ((id < 1) || (limit < 0)) {
			return Constant.STATUS_INVALID;
		}
		List<AttachmentFeedDp> list = new ArrayList<AttachmentFeedDp>();
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("page"))) {
			list = attachmentFeedService.getLimitedAttachmentFeedDpsByBookId(
					id, limit, DaoConstant.AfeedPageOrder);

		} else {
			list = attachmentFeedService.getLimitedAttachmentFeedDpsByBookId(
					id, limit);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_ATTACHMENTFEED_LIST,
				list);
	}
}
