package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.validation.DeleteValidation;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Question queryQuestionById(int id) {
		// TODO Auto-generated method stub
		return (Question) getCurrentSession().get(Question.class, id);
	}

	@Override
	public int addQuestion(Question question) {
		// TODO Auto-generated method stub
		if (isQuestionExists(question)) {
			return -1;
		}
		getCurrentSession().save(question);
		return 1;
	}

	@Override
	public int deleteQuestion(Question question) {
		// TODO Auto-generated method stub
		if (!isQuestionExists(question)) {
			return -1;
		}
		getCurrentSession().delete(question);
		return 1;
	}

	@Override
	public int updateQuestion(Question question) {
		// TODO Auto-generated method stub
		if (!isQuestionExists(question)) {
			return -1;
		}
		getCurrentSession().update(question);
		return 1;
	}

	@Override
	public boolean isQuestionExists(Question question) {
		// TODO Auto-generated method stub
		if (question == null) {
			return false;
		}
		String hql = "select count(*) from Question as q where q.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, question.getId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> queryQuestionByAskerId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Question as q where q.askerId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> queryQuestionByBookId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Question as q where q.bookId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();

	}

	/*
	 * property:favoriteNum commentNum followNum
	 */
	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Question as q set q." + property + "=q."
				+ property + "+1 and where q.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Question as q set q." + property + "=q."
				+ property + "-1 and where q.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();

	}

	@Override
	public int getLatestQuestionIdByBookId(int id) {
		// TODO Auto-generated method stub
		String hql = "select max(q.id) from Question as q where q.bookId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Integer) query.uniqueResult();
	}

	@Override
	public boolean checkDeleteValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Question as q where q.askerId =? and q.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}
}
