package com.didihe1988.picker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.NoteService;
import com.didihe1988.picker.service.QuestionService;
import com.didihe1988.picker.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private FollowService followService;

	@Autowired
	private NoteService noteService;
	
	/**
	 * @description 个人信息
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	/**
	 * @description 回答过的问题
	 */
	@RequestMapping(value = "/user/{id}/answers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Answer> getAnswers(@PathVariable int id) {
		return answerService.getAnswerByReplierId(id);
	}
	
	/**
	 * @description 问过的问题 
	 */
	@RequestMapping(value = "/user/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Question> getQuestions(@PathVariable int id) {
		return questionService.getQuestionByAskerId(id);
	}
	
	/**
	 * @description 写过的笔记  自己的笔记显示全部  别人的只显示public部分
	 */
	@RequestMapping(value = "/user/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Note> getNotes(@PathVariable int id) {
		return noteService.getALlNoteListByUserId(id);
	}
	
	
}
