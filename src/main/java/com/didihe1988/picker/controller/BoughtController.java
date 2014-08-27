package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class BoughtController {
	@Autowired
	private BoughtService boughtService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/bought/add_byisbn.do")
	public String addBought(HttpServletRequest request) {
		String isbn = request.getParameter("isbn");
		int bookId = bookService.getBookIdByISBN(isbn);
		int userId = HttpUtils.getSessionUser(request).getId();
		// logger.debug(userId + "-" + bookId);
		boughtService.addBought(new Bought(userId, bookId));
		return "/book/list";
	}

	@RequestMapping(value = "/bought/{id}delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String delete(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = boughtService.deleteBought(new Bought(userId, id));
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}
}
