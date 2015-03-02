package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.type.SetType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.json.BookJson;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.google.gson.Gson;

@Controller
public class BrowseController {
	@Autowired
	private UserService userService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private BookService bookService;

	/*
	 * id -bookId
	 */
	@RequestMapping(value = "/browse/{id}/{page}")
	public String browse(@PathVariable int id, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((id < 1) || (page < 0)) {
			return "error";
		}

		if (bookService.isBookExistsById(id)) {
			Book book = bookService.getBookById(id);
			List<FeedDp> feedList = feedService.getFeedDpListForBrowse(id,
					HttpUtils.getSessionUserId(request));
			model.addAttribute("book", book);
			model.addAttribute("page", page);
			model.addAttribute("feedList", feedList);
			String type = (String) request.getParameter("type");
			if ((type == null) || (type.equals("")) || (!isTypeValid(type))) {
				type = "all";
			}
			model.addAttribute("type", type);
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/feeds/{page}")
	public String getFeeds(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","feed");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/questions/{page}")
	public String getQuestions(@PathVariable int bookId,
			@PathVariable int page, Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","question");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/notes/{page}")
	public String getNotes(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","note");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/attachments/{page}")
	public String getAttachments(@PathVariable int bookId,
			@PathVariable int page, Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","attachment");
			return "browse";
		} else {
			return "error";
		}
	}

	private void addCommonModel(Model model, int bookId, int page) {
		Book book = bookService.getBookById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("page", page);
	}

	private boolean isTypeValid(String type) {
		if (type.equals("attachment") || type.equals("question")
				|| type.equals("note")) {
			return true;
		}
		return false;
	}

	// http://localhost:8090/page/123/0?filter=all&direction=up
	@RequestMapping(value = "/page/{bookId}/0", produces = "application/json")
	public @ResponseBody String bookDetail(@PathVariable int bookId) {
		if (bookId < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Book book = bookService.getBookById(bookId);

		Gson gson = new Gson();
		return gson.toJson(getBookJson(book));
	}

	private BookJson getBookJson(Book book) {
		return new BookJson(
				book.getBookName(),
				book.getImageUrl(),
				book.getWriter(),
				book.getPress(),
				book.getDate(),
				"ÔÝÎÞ¼ò½é");
	}

}
