package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class BoughtController {
	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	@Autowired
	private MessageFactory messageFactory;

	@RequestMapping(value = "/bought/add_byisbn.do")
	public String addBought(HttpServletRequest request) {
		String isbn = request.getParameter("isbn");
		int bookId = bookService.getBookIdByISBN(isbn);
		int userId = HttpUtils.getSessionUser(request).getId();
		// logger.debug(userId + "-" + bookId);
		boughtService.addBought(new Bought(userId, bookId));
		messageFactory.addMessage(userId, userId, bookId,
				Message.MESSAGE_FOLLOWED_ADDBOUGHT);
		return "/book/list";
	}

	@RequestMapping(value = "/bought/delete.do")
	public String delete(HttpServletRequest request) {
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		int userId = HttpUtils.getSessionUser(request).getId();
		boughtService.deleteBought(new Bought(userId, bookId));
		return "/book/list";
	}
}
