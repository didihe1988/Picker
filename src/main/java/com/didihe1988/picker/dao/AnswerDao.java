package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.dao.daoInterface.SearchOperation;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.dp.AnswerDp;

public interface AnswerDao extends OperateValidation, NumOperation,
		SearchOperation<Answer> {
	public Answer queryAnswerById(int id);

	public int addAnswer(Answer answer);

	public int deleteAnswer(Answer answer);

	public int updateAnswer(Answer answer);

	public boolean isAnswerExistsById(int id);

	public boolean isAnswerExsitsByKey(int questionId, int replierId);

	public List<Answer> queryAnswerByQuestionId(int id);

	public List<AnswerDp> queryAnswerDpByQuestionId(int id);

	public List<Answer> queryAnswerByReplierId(int id);

	public List<AnswerDp> queryAnswerDpByReplierId(int id);

	public List<Answer> queryAnswerByReplierId(int id, int page);

	public int queryLatestAnswerIdByQuestionId(int id);

	public boolean isAnswerOfUserAlreadyExists(int userId);

}
