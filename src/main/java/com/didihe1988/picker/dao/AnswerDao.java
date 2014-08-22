package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Answer;

public interface AnswerDao extends DeleteValidation, NumOperation {
	public Answer queryAnswerById(int id);

	public int addAnswer(Answer answer);

	public int deleteAnswer(Answer answer);

	public int updateAnswer(Answer answer);

	public boolean isAnswerExists(Answer answer);

	public List<Answer> queryAnswerByQuestionId(int id);

	public List<Answer> queryAnswerByReplierId(int id);

	public int queryLatestAnswerIdByQuestionId(int id);

	public boolean isAnswerOfUserAlreadyExists(int userId);

}
