package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.factory.MessageFactory;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/comment/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int commentedId = HttpUtils.getIntegerFromReqeust(request,
				"commentedId");
		int producerId = HttpUtils.getIntegerFromReqeust(request, "producerId");
		String content = HttpUtils.getStringFromReqeust(request, "content");
		int type = HttpUtils.getIntegerFromReqeust(request, "type");
		// addComment
		Comment comment = new Comment(commentedId, producerId, content, type);
		commentService.addComment(comment);
		// add Message
		int commentId = commentService.getLatestCommentIdByUserId(userId);
		messageFactory.addMessage(userId, userId, commentId,
				Message.MESSAGE_FOLLOWED_ADDCOMMENT);
		// return "book/detail.do?bookId=" + comment.getBookId();
		return "";
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
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService
				.incrementCommentFavorite(commentId, userId);

		/*
		 * XXX赞了您的评论 两个函数都查询了comemnt 有待优化
		 */
		if (status == Status.SUCCESS) {
			Comment comment = commentService.getCommentById(commentId);
			String relatedSourceContent = StringUtils.confineStringLength(
					comment.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(comment.getProducerId(),
					Message.MESSAGE_YOUR_COMMENT_FAVORITED, userId, userName,
					commentId, relatedSourceContent);
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
