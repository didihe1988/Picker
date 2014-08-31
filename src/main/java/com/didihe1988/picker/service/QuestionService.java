package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.QuestionDp;

public interface QuestionService {
	public int addQuestion(Question question);

	public int deleteQuestion(Question question, int userId);

	public int deleteQuestionById(int id, int userId);

	public int updateQuestion(Question question,int userId);

	public Question getQuestionById(int id);

	public QuestionDp getQuestionDpByQuestionId(int id);

	public boolean isQuestionExistsById(int id);

	public List<Question> getQuestionListByAskerId(int id);

	public List<Question> getQuestionListByBookId(int id);

	public List<QuestionDp> getQuestionDpListByAskerId(int id);

	public List<QuestionDp> getQuestionDpListByBookId(int id);

	public int getLatestQuestionIdByBookId(int id);

	public boolean checkOperateValidation(int userId, int questionId);

}
