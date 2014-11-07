package com.didihe1988.picker.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.AttachmentFeedDp;
import com.didihe1988.picker.model.dp.FeedDp;
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
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		long startTime = System.currentTimeMillis();
		List<FeedDp> list = feedService.getFeedDpListByBookId(id,
				Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request));
		long endTime = System.currentTimeMillis();
		System.out.println("spend: " + (endTime - startTime));
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/json/book/{id}/notedps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNoteDps(@PathVariable int id, HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<FeedDp> list = feedService.getFeedDpListByBookId(id,
				Feed.TYPE_NOTE, HttpUtils.getSessionUserId(request));
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/**
	 * @description 本书附件
	 */
	@RequestMapping(value = "/json/book/{id}/attachmentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAttachments(@PathVariable int id,
			HttpServletRequest request) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<AttachmentFeedDp> list = attachmentFeedService
				.getAttachmentFeedDpsByBookId(id);
		return JsonUtils
				.getJsonObjectString(Constant.KEY_ATTACHMENTFEED_LIST, list);
	}
}
