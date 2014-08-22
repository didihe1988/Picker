package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Comment getComment(@PathVariable int id) {
		Comment comment = commentService.getCommentById(id);
		return comment;
	}

	@RequestMapping(value = "/comment/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {
		/*
		 * 添加评论
		 */
		int userId = HttpUtils.getSessionUserId(request);
		int commentedId = HttpUtils.getIntegerFromReqeust(request,
				"commentedId");
		int producerId = HttpUtils.getIntegerFromReqeust(request, "producerId");
		String content = HttpUtils.getStringFromReqeust(request, "content");
		int type = HttpUtils.getIntegerFromReqeust(request, "type");
		Comment comment = new Comment(commentedId, producerId, content, type);
		commentService.addComment(comment);

		int commentId = commentService.getLatestCommentIdByUserId(userId);

		String userName = HttpUtils.getSessionUserName(request);
		// relatedSourceContent-评论内容
		String relatedSourceContent = StringUtils.confineStringLength(content,
				Constant.MESSAGE_LENGTH);
		/*
		 * 小明 评论了您的问题
		 */
		if (type == Comment.COMMENT_QUESTION) {
			Question question = questionService.getQuestionById(commentedId);
			messageService.addMessageByRecerver(question.getAskerId(),
					Message.MESSAGE_YOUR_QUESTION_COMMENTED, producerId,
					userName, commentId, relatedSourceContent);
		}

		/*
		 * 小明 评论了您的回答
		 */
		if (type == Comment.COMMENT_ANSWER) {
			Answer answer = answerService.getAnswerById(commentedId);
			messageService.addMessageByRecerver(answer.getReplierId(),
					Message.MESSAGE_YOUR_ANSWER_COMMENTED, userId, userName,
					commentId, relatedSourceContent);
		}
		// return "book/detail.do?bookId=" + comment.getBookId();
		return "";
	}

	/*
	 * @RequestMapping(value = "/comment/list.do") public String
	 * list(HttpServletRequest request, ModelMap modelMap) { int bookId =
	 * HttpUtils.getIntegerFromReqeust(request, "bookId"); List<Comment>
	 * commentList = commentService.getCommentListByAnswerId(id) assert
	 * commentList != null; for (int i = 0; i < commentList.size(); i++) {
	 * System.out.println(commentList.get(i).toString()); }
	 * modelMap.addAttribute("commentList", commentList); return
	 * "/comment/list.do"; }
	 */

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

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的评论 两个函数都查询了comemnt 有待优化
			 */
			Comment comment = commentService.getCommentById(commentId);
			String relatedSourceContent = StringUtils.confineStringLength(
					comment.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(comment.getProducerId(),
					Message.MESSAGE_YOUR_COMMENT_FAVORITED, userId, userName,
					commentId, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的评论
			 */
			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_COMMENT, userId,
					userName, commentId, relatedSourceContent);
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
