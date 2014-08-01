package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Question;

public interface QuestionDao {
	public Question queryQuestionById(int id);

	public int addQuestion(Question question);

	public int deleteQuestion(Question question);

	public int updateQuestion(Question question);

	public boolean isQuestionExists(Question question);

	public List<Question> queryQuestionByAskerId(int id);

	public List<Question> queryQuestionByBookId(int id);

	public int getLatestQuestionIdByBookId(int id);

}
