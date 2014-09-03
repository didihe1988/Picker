package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Follow;
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

	@Autowired
	private FollowDao followDao;

	@Autowired
	private FavoriteDao favoriteDao;

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
		// userDao.incrementNum(Constant.QUESTION_NUM, question.getAskerId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteQuestion(Question question, int userId) {
		// TODO Auto-generated method stub
		if (question == null) {
			return Status.NULLPOINTER;
		}
		if (!questionDao.checkOperateValidation(userId, question.getId())) {
			return Status.INVALID;
		}
		questionDao.deleteQuestion(question);
		return Status.SUCCESS;
	}

	@Override
	public int deleteQuestionById(int id, int userId) {
		// TODO Auto-generated method stub
		return deleteQuestion(questionDao.queryQuestionById(id), userId);
	}

	@Override
	public int updateQuestion(Question question, int userId) {
		// TODO Auto-generated method stub
		if (question == null) {
			return Status.NULLPOINTER;
		}
		if (!questionDao.checkOperateValidation(userId, question.getId())) {
			return Status.INVALID;
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
	public boolean isQuestionExistsById(int id) {
		// TODO Auto-generated method stub
		return questionDao.isQuestionExistsById(id);
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

	// ºÏ≤‚ «∑Òœ‘ æ…æ≥˝∞¥≈•
	@Override
	public boolean checkOperateValidation(int userId, int questionId) {
		// TODO Auto-generated method stub
		return questionDao.checkOperateValidation(userId, questionId);
	}

	@Override
	public int getLatestQuestionIdByBookId(int id) {
		// TODO Auto-generated method stub
		return questionDao.getLatestQuestionIdByBookId(id);
	}

	@Override
	public QuestionDp getQuestionDpByQuestionId(int id, int userId) {
		// TODO Auto-generated method stub
		Question question = getQuestionById(id);
		return getQuestionDpByQuestion(question, userId);
	}

	private QuestionDp getQuestionDpByQuestion(Question question, int userId) {
		// TODO Auto-generated method stub
		String bookName = bookDao.queryBookById(question.getBookId())
				.getBookName();
		String askerName = userDao.queryUserById(question.getAskerId())
				.getUsername();
		String askerAvatarUrl = userDao.queryUserById(question.getAskerId())
				.getAvatarUrl();
		QuestionDp questionDp = new QuestionDp(question, bookName, askerName,
				askerAvatarUrl, followDao.isFollowExistsByKey(
						Follow.FOLLOW_QUESTION, userId, question.getId()),
				favoriteDao.isFavoriteExistsByKey(userId, question.getId(),
						Favorite.FAVORITE_QUESTION));
		return questionDp;
	}

	private List<QuestionDp> getQuestionDpListFromQuestionList(
			List<Question> questionList, int userId) {
		List<QuestionDp> list = new ArrayList<QuestionDp>();
		for (Question question : questionList) {
			QuestionDp questionDp = getQuestionDpByQuestion(question, userId);
			list.add(questionDp);
		}
		return list;
	}

	@Override
	public List<QuestionDp> getQuestionDpListByBookId(int id, int userId) {
		// TODO Auto-generated method stub
		return getQuestionDpListFromQuestionList(getQuestionListByBookId(id),
				userId);
	}

	@Override
	public List<QuestionDp> getQuestionDpListByAskerId(int id, int userId) {
		// TODO Auto-generated method stub
		return getQuestionDpListFromQuestionList(getQuestionListByAskerId(id),
				userId);
	}

}
