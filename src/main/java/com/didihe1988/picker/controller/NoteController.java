package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/note/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNote(@PathVariable int id) {
		Note note = noteService.getNoteById(id);
		return JsonUtils.getJsonObjectString("note", note);
	}

	/**
	 * @description 该笔记下的评论
	 */
	@RequestMapping(value = "/note/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_NOTE);
		return JsonUtils.getJsonObjectString("commentList", list);
	}

	@RequestMapping(value = "/note/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteNote(@PathVariable int id) {
		int status = noteService.deleteNoteById(id);
		return JsonUtils.getJsonObjectString("status", status);
	}

	@RequestMapping(value = "/note/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		int noteId = HttpUtils.getIntegerFromReqeust(request, "noteId");
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementNoteFavorite(noteId, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的笔记
			 */
			Note note = noteService.getNoteById(noteId);
			String relatedSourceContent = StringUtils.confineStringLength(
					note.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(note.getUserId(),
					Message.MESSAGE_YOUR_ANSWER_FAVORITED, userId, userName,
					noteId, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的笔记
			 */

			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_NOTE, userId, userName,
					noteId, relatedSourceContent);
		}
		return "";
	}
}
