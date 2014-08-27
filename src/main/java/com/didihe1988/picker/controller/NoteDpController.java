package com.didihe1988.picker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.CommentDp;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.NoteDp;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class NoteDpController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/note/{id}/dp", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNoteDp(@PathVariable int id) {
		NoteDp noteDp = noteService.getNoteDpByNoteId(id);
		return JsonUtils.getJsonObjectString("note", noteDp);
	}

	/**
	 * @description 该笔记下的评论
	 */
	@RequestMapping(value = "/note/{id}/commentdps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCommetDps(@PathVariable int id) {
		List<CommentDp> list = commentService.getCommentDpListByCommentedId(id,
				Comment.COMMENT_NOTE);
		return JsonUtils.getJsonObjectString("commentList", list);
	}

}
