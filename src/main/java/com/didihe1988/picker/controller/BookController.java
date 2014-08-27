package com.didihe1988.picker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private NoteService noteService;

	/**
	 * @description 本书的详细内容
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBook(@PathVariable int id) {
		Book book = bookService.getBookById(id);
		return JsonUtils.getJsonObjectString("book", book);
	}

	/**
	 * @description 本书下提出的问题
	 */
	@RequestMapping(value = "/book/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		List<Question> list = questionService.getQuestionListByBookId(id);
		return JsonUtils.getJsonObjectString("questionList", list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/book/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNotes(@PathVariable int id) {
		List<Note> list = noteService.getPublicNoteListByBookId(id);
		return JsonUtils.getJsonObjectString("noteList", list);
	}

}
