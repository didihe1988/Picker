package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.utils.HttpUtils;

@RestController
public class BookDetailController {
	@Autowired
	private BookService bookService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@RequestMapping(value = "book/detail.do")
	public String showBookDetailAndComment(HttpServletRequest request,
			ModelMap modelMap) {
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		Book book = bookService.getBookById(bookId);
		/*
		 * List<Comment> commentList =
		 * commentService.getCommentByBookId(bookId); assert commentList !=
		 * null; for (int i = 0; i < commentList.size(); i++) {
		 * System.out.println(commentList.get(i).toString()); }
		 */
		// modelMap.addAttribute("commentList", commentList);
		modelMap.addAttribute("book", book);
		return "/bookdetail";
	}
	
}
