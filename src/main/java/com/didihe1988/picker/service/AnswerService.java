package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.AnswerDp;

public interface AnswerService {
	public int addAnswer(Answer answer);

	public int deleteAnswer(Answer answer, int userId);

	public int deleteAnswerById(int id, int userId);

	public int updateAnswer(Answer answer);

	public boolean isAnswerExists(Answer answer);

	public boolean isAnswerOfUserExists(int userId);

	public Answer getAnswerById(int id);

	public List<Answer> getAnswerListByQuestionId(int id);

	public List<Answer> getAnswerListByReplierId(int id);

	public List<AnswerDp> getAnswerDpListByQuestionId(int id);

	public List<AnswerDp> getAnswerDpListByReplierId(int id);

	public int getLatestAnswerIdByQuestionId(int id);

	public boolean checkDeleteValidation(int userId, int answerId);

	public AnswerDp getAnswerDpByAnswerId(int id);

}
