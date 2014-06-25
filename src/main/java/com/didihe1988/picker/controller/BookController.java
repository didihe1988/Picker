package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.OwnBookService;

@Controller
@RequestMapping("/book")
public class BookController extends BaseController {
	@Autowired
	BookService bookService;
	@Autowired
	OwnBookService ownBookService;

	private final static Logger logger = LoggerFactory
			.getLogger(BookController.class);

	@RequestMapping
	public String getStartPage() {
		return "book/list";
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int userId = getSessionUser(request).getId();
		logger.debug("userId:" + userId);
		List<Integer> bookIdList = ownBookService.findAllByUserId(userId);
		assert bookIdList != null;
		List<Book> bookList = new ArrayList<Book>();
		for (int i = 0; i < bookIdList.size(); i++) {
			bookList.add(bookService.findBookById(bookIdList.get(i)));
		}
		logger.debug(bookList.toString());
		modelMap.addAttribute("userList", bookList);
		return "/book/list";
	}

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, String isbn) {
		int bookId = bookService.getBookIdByISBN(isbn);
		int userId = getSessionUser(request).getId();
		logger.debug(userId + "-" + bookId);
		ownBookService.addBook(bookId, userId);
		return "/book/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, int bookId) {
		int userId = getSessionUser(request).getId();
		ownBookService.deleteBook(bookId, userId);
		return "/book/list";
	}

	// 是跳转到详细的书籍介绍，还是做成手风琴式？应该是详细的介绍，像歌曲一样
	@RequestMapping(value = "/detail")
	public String detail(int bookId, Model model) {
		Book book = bookService.findBookById(bookId);
		assert book != null;
		model.addAttribute("book", book);
		return "book/detail";
	}

}
