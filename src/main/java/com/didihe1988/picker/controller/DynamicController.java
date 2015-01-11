package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class DynamicController {
	@Autowired
	private MessageService messageService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	/**
	 * @description 显示我的动态
	 */
	/*
	 * localhost:8090
	 */
	@RequestMapping(value = "")
	public String dynamic(Model model, HttpServletRequest request) {
		int curUserId = HttpUtils.getSessionUserId(request);
		model.addAttribute("messageList",
				messageService.getDynamicsByUserId(curUserId));
		model.addAttribute("bookList", getBooks(curUserId));
		return "index";
	}

	private List<Book> getBooks(int userId) {
		final List<Bought> boughtList = boughtService.getBoughtByUserId(userId);
		List<Book> bookList = new ArrayList<Book>();
		for (Bought bought : boughtList) {
			Book book = bookService.getBookById(bought.getBookId());
			bookList.add(book);
		}
		return bookList;
	}
}
