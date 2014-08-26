package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.AnswerDp;
import com.didihe1988.picker.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private UserDao userDao;

	@Override
	public int addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		int status = answerDao.addAnswer(answer);
		if (status == -1) {
			return Status.EXISTS;
		}

		/*
		 * Question answerNum++
		 */
		questionDao.incrementNum(Constant.ANSWER_NUM, answer.getQuestionId());
		/*
		 * User answerNum++
		 */
		userDao.incrementNum(Constant.ANSWER_NUM, answer.getReplierId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		// 删除之间检验是否存在
		if (!answerDao.checkDeleteValidation(answer.getReplierId(),
				answer.getId())) {
			return Status.INVALID;
		}
		answerDao.deleteAnswer(answer);
		return Status.SUCCESS;
	}

	@Override
	public int updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		int status = answerDao.updateAnswer(answer);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isAnswerExists(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return false;
		}
		return answerDao.isAnswerExists(answer);
	}

	@Override
	public boolean isAnswerOfUserExists(int userId) {
		// TODO Auto-generated method stub
		return answerDao.isAnswerOfUserAlreadyExists(userId);
	}

	@Override
	public Answer getAnswerById(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerById(id);
	}

	@Override
	public List<Answer> getAnswerByQuestionId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerByQuestionId(id);
	}

	@Override
	public int getLatestAnswerIdByQuestionId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryLatestAnswerIdByQuestionId(id);
	}

	@Override
	public boolean checkDeleteValidation(int userId, int answerId) {
		// TODO Auto-generated method stub
		return answerDao.checkDeleteValidation(userId, answerId);
	}

	@Override
	public List<Answer> getAnswerByReplierId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerByReplierId(id);
	}

	@Override
	public AnswerDp getAnswerDpByAnswerId(int id) {
		// TODO Auto-generated method stub
		Answer answer = answerDao.queryAnswerById(id);
		String questionName = questionDao.queryQuestionById(
				answer.getQuestionId()).getTitle();
		String replierName = userDao.queryUserById(answer.getReplierId())
				.getUsername();
		return new AnswerDp(answer, questionName, replierName);
	}

}
