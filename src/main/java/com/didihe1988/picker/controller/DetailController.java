package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class DetailController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/detail/{id}")
	public String question(@PathVariable int id, Model model,
			HttpServletRequest request) {
		if (feedService.isFeedExistsById(id)) {
			Feed feed = feedService.getFeedById(id);
			UserDp userDp = userService.getUserDpByUserId(feed.getUserId(),
					HttpUtils.getSessionUserId(request));
			model.addAttribute("user", userDp);
			model.addAttribute("question", feed);
			return "detail";
		} else {
			return "error";
		}

	}

	// http://localhost:5000/detail/1234/15/new

	@RequestMapping(value = "/detail/{bookId}/{page}/new")
	public String newFeed(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if (bookService.isBookExistsById(bookId)) {
			Book book = bookService.getBookById(bookId);
			model.addAttribute("book", book);
			return "new";
		} else {
			return "error";
		}

	}
}