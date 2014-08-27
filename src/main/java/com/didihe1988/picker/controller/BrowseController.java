package com.didihe1988.picker.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.google.gson.Gson;

@RestController
public class BrowseController {
	@Autowired
	private BookService bookService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/browse/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBrowse(@PathVariable int id) {
		JSONObject jsonObject = new JSONObject();
		/*
		 * book
		 */
		Book book = bookService.getBookById(id);
		Gson gson = new Gson();
		String bookJson = gson.toJson(book);
		jsonObject.put(Constant.KEY_BOOK, bookJson);
		/*
		 * questionList
		 */
		List<QuestionDp> list = questionService.getQuestionDpListByBookId(id);
		jsonObject.put(Constant.KEY_QUESTION_LIST, list);

		return jsonObject.toString();
	}
}
