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
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.OwnBookService;

@Controller
public class BookController extends BaseController {
	@Autowired
	private BookService bookService;
	@Autowired
	private BoughtService boughtService;

	private final static Logger logger = LoggerFactory
			.getLogger(BookController.class);

	/*
	 * @RequestMapping public String getStartPage() { return "book/list"; }
	 */

	@RequestMapping(value = "/book/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int userId = getSessionUser(request).getId();
		logger.debug("userId:" + userId);
		List<Bought> boughtList = boughtService.getBoughtByUserId(userId);
		assert boughtList != null;
		List<Book> bookList = new ArrayList<Book>();
		for (int i = 0; i < boughtList.size(); i++) {
			Book book = bookService.findBookById(boughtList.get(i).getBookId());
			bookList.add(book);
		}
		// logger.debug(bookList.toString());
		for (int i = 0; i < bookList.size(); i++) {
			System.out.println(bookList.get(i).toString());
		}
		modelMap.addAttribute("bookList", bookList);
		return "/booklist";
	}

	/*
	 * @RequestMapping(value = "/book/list_android.do",produces =
	 * "application/json") public @ResponseBody List<Book>
	 * list(HttpServletRequest request) { System.out.println("wolegeca"); int
	 * userId = getSessionUser(request).getId(); logger.debug("userId:" +
	 * userId); List<Integer> bookIdList =
	 * ownBookService.findAllByUserId(userId); assert bookIdList != null;
	 * List<Book> bookList = new ArrayList<Book>(); for (int i = 0; i <
	 * bookIdList.size(); i++) {
	 * bookList.add(bookService.findBookById(bookIdList.get(i))); } return
	 * bookList; }
	 */

	@RequestMapping(value = "/book/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.findBookById(bookId);
		assert book != null;
		model.addAttribute("book", book);
		return "book/detail";
	}

}
