package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.display.AnswerDp;
import com.didihe1988.picker.utils.DaoUtils;

@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		return isAnswerExistsById(id);
	}

	@Override
	public Answer queryModelById(int id) {
		// TODO Auto-generated method stub
		return (Answer) getCurrentSession().get(Answer.class, id);
	}

	@Override
	public int addModel(Answer answer) {
		// TODO Auto-generated method stub
		if (isAnswerExsitsByKey(answer.getQuestionId(), answer.getReplierId())) {
			return -1;
		}
		getCurrentSession().save(answer);
		return 1;
	}

	@Override
	public int deleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (!isAnswerExistsById(answer.getId())) {
			return -1;
		}
		getCurrentSession().delete(answer);
		return 1;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete Answer where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(Answer answer) {
		// TODO Auto-generated method stub
		if (!isAnswerExistsById(answer.getId())) {
			return -1;
		}
		getCurrentSession().update(answer);
		return 1;
	}

	@Override
	public boolean isAnswerExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as a where a.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAnswerExsitsByKey(int questionId, int replierId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as a where a.questionId = ? and a.replierId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, questionId);
		query.setInteger(1, replierId);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<AnswerDp> queryAnswerDpByQuestionId(int id) {
		// TODO Auto-generated method stub
		String hql = "select new com.didihe1988.picker.model.display.AnswerDp(a,q.title,u.username,u.signature,u.avatarUrl) from Answer a ,Feed q,User u where a.questionId=? and a.questionId = q.id and a.replierId = u.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> queryAnswerByReplierId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Answer as a where a.replierId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnswerDp> queryAnswerDpByReplierId(int id) {
		// TODO Auto-generated method stub
		String hql = "select new com.didihe1988.picker.model.display.AnswerDp(a,q.title) from Answer a ,Feed q where a.replierId=? and a.questionId = q.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> queryAnswerByReplierId(int id, int page) {
		// TODO Auto-generated method stub
		String hql = "from Answer as a where a.replierId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		DaoUtils.setLimit(query, page - 1);
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

	@Override
	public boolean isAnswerOfUserAlreadyExists(int userId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as a where a.replierId = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as a where a.replierId =? and a.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	/*
	 * property:favoriteNum commentNum
	 */
	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Answer as a set a." + property + "=a." + property
				+ "+1 where a.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Answer as a set a." + property + "=a." + property
				+ "-1 where a.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> search(String string, boolean isLimited) {
		// TODO Auto-generated method stub
		String hql = "from Answer as a where a.content like ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, "%" + string + "%");
		if (isLimited) {
			DaoUtils.setLimitNum(query, Constant.DEFAULT_SEARCH_LIMITNUM);
		}
		return query.list();
	}

}
