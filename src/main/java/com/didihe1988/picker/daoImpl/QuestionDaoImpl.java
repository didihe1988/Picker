package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.model.Question;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao {
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

	@Override
	public int incrementFavoriteNum(int id) {
		// TODO Auto-generated method stub
		String hql = "update Question as q set q.favoriteNum=q.favoriteNum+1 and where q.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementFavoriteNum(int id) {
		// TODO Auto-generated method stub
		String hql = "update Question as q set q.favoriteNum=q.favoriteNum-1 and where q.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
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

}
