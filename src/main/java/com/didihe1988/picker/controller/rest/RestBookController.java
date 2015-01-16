package com.didihe1988.picker.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.DaoOrder;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Section;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.SectionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestBookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private SectionService sectionService;

	/**
	 * @description 本书的详细内容
	 */
	@RequestMapping(value = "/json/book/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBook(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		Book book = bookService.getBookById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_BOOK, book);
	}

	/**
	 * @description 本书下提出的问题
	 */
	@RequestMapping(value = "/json/book/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		System.out.println(id);
		List<Feed> list = feedService.getFeedListByBookId(id,
				Feed.TYPE_QUESTION);
		System.out.println(list);
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/json/book/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNotes(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<Feed> list = feedService.getFeedListByBookId(id, Feed.TYPE_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	@RequestMapping(value = "/json/book/{id}/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSections(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<Section> list = sectionService.getSectionsByBookId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_SECTION_LIST, list);
	}

	// 以下接口前端使用
	@RequestMapping(value = "/json/browse/{bookId}/feeds/{page}", produces = "application/json")
	public String getFeedsByPage(@PathVariable int bookId,
			@PathVariable int page, HttpServletRequest request) {
		if ((bookId < 1) || (page < 1)) {
			return Constant.STATUS_INVALID;
		}
		List<FeedDp> list = feedService.getLimitedFeedDpsByBookId(bookId,
				HttpUtils.getSessionUserId(request), page - 1,
				DaoOrder.FeedPageOrder);
		int total_page = JsonUtils.getTotalPage(list.size());
		int current_page = page;
		JSONObject jsonObject = new JSONObject();
		// page从1开始，对应的limit从0开始 ，见DaoUtils
		jsonObject.put("feedList", list);
		jsonObject.put("total_page", total_page);
		jsonObject.put("current_page", current_page);
		return jsonObject.toString();

	}

	@RequestMapping(value = "/json/browse/{bookId}/questions/{page}", produces = "application/json")
	public String getQuestionsByPage(@PathVariable int bookId,
			@PathVariable int page, HttpServletRequest request) {
		if ((bookId < 1) || (page < 1)) {
			return Constant.STATUS_INVALID;
		}
		Book book = bookService.getBookById(bookId);
		if (book == null) {
			return Constant.STATUS_INVALID;
		}
		return getFeedsByPage(bookId, book.getQuestionNum(),
				Feed.TYPE_QUESTION, HttpUtils.getSessionUserId(request), page);
	}

	@RequestMapping(value = "/json/browse/{bookId}/notes/{page}", produces = "application/json")
	public String getNotesByPage(@PathVariable int bookId,
			@PathVariable int page, HttpServletRequest request) {
		if ((bookId < 1) || (page < 1)) {
			return Constant.STATUS_INVALID;
		}
		Book book = bookService.getBookById(bookId);
		if (book == null) {
			return Constant.STATUS_INVALID;
		}
		return getFeedsByPage(bookId, book.getNoteNum(), Feed.TYPE_NOTE,
				HttpUtils.getSessionUserId(request), page);
	}

	@RequestMapping(value = "/json/browse/{bookId}/attachments/{page}", produces = "application/json")
	public String getAttachmentsByPage(@PathVariable int bookId,
			@PathVariable int page, HttpServletRequest request) {
		if ((bookId < 1) || (page < 1)) {
			return Constant.STATUS_INVALID;
		}
		Book book = bookService.getBookById(bookId);
		if (book == null) {
			return Constant.STATUS_INVALID;
		}
		return getFeedsByPage(bookId, book.getAttachmentNum(),
				Feed.TYPE_ATTACHMENT_FEED, HttpUtils.getSessionUserId(request),
				page);
	}

	private String getFeedsByPage(int bookId, int feedNum, int feedType,
			int curUserId, int page) {
		int total_page = JsonUtils.getTotalPage(feedNum);
		int current_page = page;
		JSONObject jsonObject = new JSONObject();
		// page从1开始，对应的limit从0开始 ，见DaoUtils
		jsonObject.put("feedList", feedService.getLimitedFeedDpListByBookId(
				bookId, feedType, curUserId, page - 1));
		jsonObject.put("total_page", total_page);
		jsonObject.put("current_page", current_page);
		return jsonObject.toString();
	}

}
