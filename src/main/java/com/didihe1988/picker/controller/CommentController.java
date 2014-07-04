package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.CommentService;

@Controller
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

	private final static Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	@RequestMapping(value = "/comment/add.do", method = RequestMethod.POST)
	public String add(@RequestBody Comment comment) {
		commentService.addComment(comment);
		return "/comment/list.do";
	}

	@RequestMapping(value = "/comment/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		List<Comment> commentList = commentService.getCommentByBookId(bookId);
		assert commentList != null;
		for (int i = 0; i < commentList.size(); i++) {
			System.out.println(commentList.get(i).toString());
		}
		modelMap.addAttribute("commentList", commentList);
		return "/comment/list.do";
	}

	@RequestMapping(value = "/comment/delete.do")
	public String delete(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		Comment comment = commentService.getCommentById(commentId);
		commentService.deleteComment(comment);
		return "/comment/list.do";
	}

}
