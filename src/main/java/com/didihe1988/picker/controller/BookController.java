package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.utils.HttpUtils;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private BoughtService boughtService;

	private final static Logger logger = LoggerFactory
			.getLogger(BookController.class);

	@RequestMapping(value = "/book/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int userId = HttpUtils.getSessionUser(request).getId();
		logger.debug("userId:" + userId);
		List<Bought> boughtList = boughtService.getBoughtByUserId(userId);
		assert boughtList != null;
		List<Book> bookList = new ArrayList<Book>();
		for (int i = 0; i < boughtList.size(); i++) {
			Book book = bookService.getBookById(boughtList.get(i).getBookId());
			bookList.add(book);
		}
		// logger.debug(bookList.toString());
		for (int i = 0; i < bookList.size(); i++) {
			System.out.println(bookList.get(i).toString());
		}
		modelMap.addAttribute("bookList", bookList);
		return "/booklist";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Book getBook(@PathVariable int id) {
		Book book = bookService.getBookById(id);
		return book;
	}

}
