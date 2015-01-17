package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.display.UserDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CircleService circleService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/search")
	public String search(Model model, HttpServletRequest request) {
		String content = (String) request.getParameter("s");
		if ((content == null) || (content.equals(""))) {
			return "error";
		}
		content = StringUtils.toUTF8(content);
		int curUserId = HttpUtils.getSessionUserId(request);
		List<UserDp> userList = userService.search(content, curUserId, true);
		List<Book> bookList = bookService.search(content, true);
		List<FeedDp> feedList = feedService.search(content, Feed.TYPE_ALL,
				curUserId, true);
		model.addAttribute("userList", userList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("feedList", feedList);
		return "search";
	}
}
