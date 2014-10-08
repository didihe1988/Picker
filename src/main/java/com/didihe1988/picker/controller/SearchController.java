package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;

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

	@RequestMapping(value = "/search/{content}")
	public String search(@PathVariable String content, Model model,
			HttpServletRequest request) {
		int curUserId = HttpUtils.getSessionUserId(request);
		List<UserDp> userList = userService.search(content, curUserId);
		List<Book> bookList = bookService.search(content);
		model.addAttribute("userList", userList);
		model.addAttribute("bookList", bookList);
		return "search";
	}
}
