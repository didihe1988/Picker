package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.OwnBookService;

@Controller
public class BookController extends BaseController {
	@Autowired
	private BookService bookService;
	@Autowired
	private OwnBookService ownBookService;

	private final static Logger logger = LoggerFactory
			.getLogger(BookController.class);

	/*
	 * @RequestMapping public String getStartPage() { return "book/list"; }
	 */

	@RequestMapping(value = "/book/list.do")
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
		modelMap.addAttribute("bookList", bookList);
		return "/booklist";
	}

	@RequestMapping(value = "/book/list_android.do",produces = "application/json")
	public @ResponseBody List<Book> list(HttpServletRequest request) {
		System.out.println("wolegeca");
		int userId = getSessionUser(request).getId();
		logger.debug("userId:" + userId);
		List<Integer> bookIdList = ownBookService.findAllByUserId(userId);
		assert bookIdList != null;
		List<Book> bookList = new ArrayList<Book>();
		for (int i = 0; i < bookIdList.size(); i++) {
			bookList.add(bookService.findBookById(bookIdList.get(i)));
		}
		return bookList;
	}

	@RequestMapping(value = "/book/add.do")
	public String add(HttpServletRequest request, String isbn) {
		int bookId = bookService.getBookIdByISBN(isbn);
		int userId = getSessionUser(request).getId();
		logger.debug(userId + "-" + bookId);
		ownBookService.addBook(bookId, userId);
		return "/book/list";
	}

	@RequestMapping(value = "/book/delete.do")
	public String delete(HttpServletRequest request, int bookId) {
		int userId = getSessionUser(request).getId();
		ownBookService.deleteBook(bookId, userId);
		return "/book/list";
	}

	@RequestMapping(value = "/book/detail.do")
	public String detail(int bookId, Model model) {
		Book book = bookService.findBookById(bookId);
		assert book != null;
		model.addAttribute("book", book);
		return "book/detail";
	}

}
