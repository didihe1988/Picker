package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;

@Controller
public class BookDetailController extends BaseController {
	@Autowired
	private BookService bookService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@RequestMapping(value = "book/detail.do")
	public String showBookDetailAndComment(HttpServletRequest request,
			ModelMap modelMap) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.findBookById(bookId);
		List<Comment> commentList = commentService.getCommentByBookId(bookId);
		assert commentList != null;
		for (int i = 0; i < commentList.size(); i++) {
			System.out.println(commentList.get(i).toString());
		}
		modelMap.addAttribute("commentList", commentList);
		modelMap.addAttribute("book", book);
		return "/bookdetail";
	}
}
