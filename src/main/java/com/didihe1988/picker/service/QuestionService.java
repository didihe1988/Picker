package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Question;

public interface QuestionService {
	public int addQuestion(Question question);

	public int deleteQuestion(Question question);

	public int updateQuestion(Question question);

	public Question getQuestionById(int id);

	public boolean isQuestionExists(Question question);

	public List<Question> getQuestionByAskerId(int id);

	public List<Question> getQuestionByBookId(int id);

	public boolean checkDeleteValidation(int userId, int questionId);
}
