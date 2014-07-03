package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;

@Controller
public class BoughtController extends BaseController {
	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/bought/add_byisbn.do")
	public String addBought(HttpServletRequest request) {
		String isbn = request.getParameter("isbn");
		int bookId = bookService.getBookIdByISBN(isbn);
		int userId = getSessionUser(request).getId();
		// logger.debug(userId + "-" + bookId);
		boughtService.addBought(new Bought(userId, bookId));
		return "/book/list";
	}

	@RequestMapping(value = "/bought/delete.do")
	public String delete(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		int userId = getSessionUser(request).getId();
		boughtService.deleteBought(new Bought(userId, Integer.parseInt(bookId)));
		return "/book/list";
	}
}
