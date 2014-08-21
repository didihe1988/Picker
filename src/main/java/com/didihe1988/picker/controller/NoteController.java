package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.service.FavoriteService;
import com.didihe1988.picker.service.MessageService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.StringUtils;

@Controller
public class NoteController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FavoriteService favoriteService;

	@RequestMapping(value = "/answer/increment_favorite.do")
	public String incrementFavorite(HttpServletRequest request) {
		int noteId = HttpUtils.getIntegerFromReqeust(request, "noteId");
		int userId = HttpUtils.getSessionUserId(request);
		String userName = HttpUtils.getSessionUserName(request);
		int status = favoriteService.incrementNoteFavorite(noteId, userId);

		/*
		 * XXXÔÞÁËÄúµÄ±Ê¼Ç
		 */
		if (status == Status.SUCCESS) {
			Note note = noteService.getNoteById(noteId);
			String relatedSourceContent = StringUtils.confineStringLength(
					note.getContent(), Constant.MESSAGE_LENGTH);
			messageService.addMessageByRecerver(note.getUserId(),
					Message.MESSAGE_YOUR_ANSWER_FAVORITED, userId, userName,
					noteId, relatedSourceContent);
		}

		return "";
	}
}
