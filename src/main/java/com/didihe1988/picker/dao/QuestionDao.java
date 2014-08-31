package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Question;

public interface QuestionDao extends OperateValidation, NumOperation {
	public Question queryQuestionById(int id);

	public int addQuestion(Question question);

	public int deleteQuestion(Question question);

	public int updateQuestion(Question question);

	public boolean isQuestionExistsById(int id);

	public List<Question> queryQuestionByAskerId(int id);

	public List<Question> queryQuestionByBookId(int id);

	public int getLatestQuestionIdByBookId(int id);

}
