package com.didihe1988.picker.controller;

import java.util.ArrayList;
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
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private FollowService followService;

	@Autowired
	private MessageService messageService;

	private final static Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	@RequestMapping(value = "/comment/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		int userId=HttpUtils.getSessionUser(request).getId();
		String content=request.getParameter("content");
		int bookId=HttpUtils.getIntegerFromReqeust(request, "bookId");
		int receiverId=HttpUtils.getIntegerFromReqeust(request, "receiverId");
		int producerId=HttpUtils.getIntegerFromReqeust(request, "producerId");
		//add Message
		List<Follow> followList=followService.getFollowByFollowedUserId(userId);
		for(int i=0;i<followList.size();i++)
		{
			Follow  follow=followList.get(i);
			Message message=new Message(follow.getFollowerId(), false, Message.MESSAGE_FOLLOWED_COMMENT, userId);
			messageService.addMessage(message);
		}
		
		//addComment
		Comment comment=new Comment(receiverId, producerId, content);
		commentService.addComment(comment);
		return "book/detail.do?bookId=" + comment.getBookId();
	}

	@RequestMapping(value = "/comment/list.do")
	public String list(HttpServletRequest request, ModelMap modelMap) {
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
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
		int commentId = HttpUtils.getIntegerFromReqeust(request, "commentId");
		Comment comment = commentService.getCommentById(commentId);
		commentService.deleteComment(comment);
		return "/comment/list.do";
	}

	@RequestMapping(value = "/comment/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		System.out.println("increment_favorite");
		int commentId = HttpUtils.getIntegerFromReqeust(request, "commentId");
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		int userId = HttpUtils.getSessionUser(request).getId();
		favoriteService.incrementCommentFavorite(commentId, userId);
		// 获取关注列表
		List<Follow> followList = followService
				.getFollowByFollowedUserId(userId);
		for (int i = 0; i < followList.size(); i++) {
			Follow follow = followList.get(i);
			// 添加消息
			Message message = new Message(follow.getFollowerId(), false,
					Message.MESSAGE_FOLLOWED_FAVORITE, commentId);
			messageService.addMessage(message);
		}
		return "redirect:/book/detail.do?bookId=" + bookId;
	}

	@RequestMapping(value = "/comment/decrement_favorite.do")
	public String dcrementFavorite(HttpServletRequest request) {
		System.out.println("decrement_favorite");
		int commentId = HttpUtils.getIntegerFromReqeust(request, "commentId");
		int bookId = HttpUtils.getIntegerFromReqeust(request, "bookId");
		int userId = HttpUtils.getSessionUser(request).getId();
		favoriteService.decrementCommentFavorite(commentId, userId);
		return "redirect:/book/detail.do?bookId=" + bookId;
	}
}
