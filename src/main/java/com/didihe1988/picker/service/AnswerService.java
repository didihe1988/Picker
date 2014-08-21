package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Answer;

public interface AnswerService {
	public int addAnswer(Answer answer);

	public int deleteAnswer(Answer answer);

	public int updateAnswer(Answer answer);

	public boolean isAnswerExists(Answer answer);
	
	public boolean isAnswerOfUserExists(int userId);

	public Answer getAnswerById(int id);

	public List<Answer> getAnswerByQuestionId(int id);
	
	public int getLatestAnswerIdByQuestionId(int id);
	
	public boolean checkDeleteValidation(int userId, int answerId);
	
}
