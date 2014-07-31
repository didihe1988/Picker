package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.model.Answer;

@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Answer queryAnswerById(int id) {
		// TODO Auto-generated method stub
		return (Answer) getCurrentSession().get(Answer.class, id);
	}

	@Override
	public int addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (isAnswerExists(answer)) {
			return -1;
		}
		getCurrentSession().save(answer);
		return 1;
	}

	@Override
	public int deleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (!isAnswerExists(answer)) {
			return -1;
		}
		getCurrentSession().delete(answer);
		return 1;
	}

	@Override
	public int updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (!isAnswerExists(answer)) {
			return -1;
		}
		getCurrentSession().update(answer);
		return 1;
	}

	@Override
	public boolean isAnswerExists(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return false;
		}
		String hql = "select count(*) from Answer as a where a.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, answer.getId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> queryAnswerByQuestionId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Answer as a where a.questionId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@Override
	public int queryLatestAnswerIdByQuestionId(int id) {
		// TODO Auto-generated method stub
		/*
		 * List<Answer> answerList = this.queryAnswerByQuestionId(id); Answer
		 * answer = answerList.get(answerList.size() - 1); if (answer == null) {
		 * return 0; } return answer.getId();
		 */
		String hql = "select max(a.id) from Answer as a where a.questionId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Integer) query.uniqueResult();
	}

}
