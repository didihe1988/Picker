package com.didihe1988.picker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.NoteDp;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class BookDpController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private NoteService noteService;

	/**
	 * @description 本书下提出的问题
	 */
	@RequestMapping(value = "/book/{id}/questiondps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestionDps(@PathVariable int id) {
		List<QuestionDp> list = questionService.getQuestionDpListByBookId(id);
		return JsonUtils.getJsonObjectString("questionList", list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/book/{id}/notedps", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNoteDps(@PathVariable int id) {
		List<NoteDp> list = noteService.getPublicNoteDpListByBookId(id);
		return JsonUtils.getJsonObjectString("noteList", list);
	}
}
