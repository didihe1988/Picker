package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Answer;

public interface AnswerDao {
	public Answer queryAnswerById(int id);

	public int addAnswer(Answer answer);

	public int deleteAnswer(Answer answer);

	public int updateAnswer(Answer answer);

	public boolean isAnswerExists(Answer answer);

	public List<Answer> queryAnswerByQuestionId(int id);

}
