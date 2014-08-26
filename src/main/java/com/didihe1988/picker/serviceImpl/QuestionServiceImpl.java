package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.model.QuestionDp;
import com.didihe1988.picker.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookDao bookDao;

	@Override
	public int addQuestion(Question question) {
		// TODO Auto-generated method stub
		if (question == null) {
			return Status.NULLPOINTER;
		}
		int status = questionDao.addQuestion(question);
		if (status == -1) {
			return Status.EXISTS;
		}

		/*
		 * User questionNum++
		 */
		userDao.incrementNum(Constant.QUESTION_NUM, question.getAskerId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteQuestion(Question question) {
		// TODO Auto-generated method stub
		if (question == null) {
			return Status.NULLPOINTER;
		}
		int status = questionDao.deleteQuestion(question);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateQuestion(Question question) {
		// TODO Auto-generated method stub
		if (question == null) {
			return Status.NULLPOINTER;
		}
		int status = questionDao.updateQuestion(question);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public Question getQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionDao.queryQuestionById(id);
	}

	@Override
	public boolean isQuestionExists(Question question) {
		// TODO Auto-generated method stub
		if (question == null) {
			return false;
		}
		return questionDao.isQuestionExists(question);
	}

	@Override
	public List<Question> getQuestionListByAskerId(int id) {
		// TODO Auto-generated method stub
		return questionDao.queryQuestionByAskerId(id);
	}

	@Override
	public List<Question> getQuestionListByBookId(int id) {
		// TODO Auto-generated method stub
		return questionDao.queryQuestionByBookId(id);
	}

	// ����Ƿ���ʾɾ����ť
	@Override
	public boolean checkDeleteValidation(int userId, int questionId) {
		// TODO Auto-generated method stub
		return questionDao.checkDeleteValidation(userId, questionId);
	}

	@Override
	public int getLatestQuestionIdByBookId(int id) {
		// TODO Auto-generated method stub
		return questionDao.getLatestQuestionIdByBookId(id);
	}

	@Override
	public QuestionDp getQuestionDpByQuestionId(int id) {
		// TODO Auto-generated method stub
		Question question = getQuestionById(id);
		String bookName = bookDao.queryBookById(question.getBookId())
				.getBookName();
		String askerName = userDao.queryUserById(question.getAskerId())
				.getUsername();
		QuestionDp questionDp = new QuestionDp(question, bookName, askerName);
		return questionDp;
	}

}
