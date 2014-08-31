package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE, note);
	}

	/**
	 * @description 该笔记下的评论
	 */
	@RequestMapping(value = "/note/{id}/comments", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommets(@PathVariable int id) {
		List<Comment> list = commentService.getCommentListByCommentedId(id,
				Comment.COMMENT_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_COMMENT_LIST, list);
	}

	/**
	 * @description 删除该条笔记
	 * @condition session-userId
	 */
	@RequestMapping(value = "/note/{id}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteNote(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = noteService.deleteNoteById(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户赞了笔记
	 * @condition session-userId userName
	 */
	@RequestMapping(value = "/note/{id}/subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String subscribe(@PathVariable int id, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementNoteFavorite(id, userId);

		if (status == Status.SUCCESS) {
			/*
			 * XXX赞了您的笔记
			 */
			Note note = noteService.getNoteById(id);
			String relatedSourceContent = StringUtils.confineStringLength(
					note.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(note.getUserId(),
					Message.MESSAGE_YOUR_ANSWER_FAVORITED, userId, userName,
					id, relatedSourceContent);

			/*
			 * 通知关注者 小明 (被关注者)赞了XXX的笔记
			 */

			messageService.addMessageByFollowedUser(
					Message.MESSAGE_FOLLOWED_FAVORITE_NOTE, userId, userName,
					id, relatedSourceContent);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	/**
	 * @description 用户取消的对该笔记的赞
	 * @condition session-userId
	 */
	@RequestMapping(value = "/note/{id}/withdraw_subscribe", method = RequestMethod.GET, headers = "Accept=application/json")
	public String withdrawSubscribe(@PathVariable int id,
			HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = favoriteService.decrementNoteFavorite(id, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/note/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String add(@RequestBody Note note, HttpServletRequest request) {
		int status = noteService.addNote(note);
		if (status == Status.SUCCESS) {
			addNoteMessage(note, request);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/note/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public String update(@RequestBody Note note, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		int status = noteService.updateNote(note, userId);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void addNoteMessage(Note note, HttpServletRequest request) {
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		String relatedSourceContent = StringUtils.confineStringLength(
				note.getTitle(), Constant.MESSAGE_LENGTH);
		int noteId = noteService.getLatestNoteIdByUserId(note.getUserId());
		messageService.addMessageByFollowedUser(
				Message.MESSAGE_FOLLOWED_ADDNOTE, userId, userName, noteId,
				relatedSourceContent);
	}
}
