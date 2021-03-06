package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.model.Follow;

@Repository
@Transactional
public class FollowDaoImpl implements FollowDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Follow queryModelById(int id) {
		// TODO Auto-generated method stub
		return (Follow) getCurrentSession().get(Follow.class, id);
	}

	@Override
	public int addModel(Follow follow) {
		// TODO Auto-generated method stub
		if (isFollowExistsByKey(follow.getSourceType(), follow.getFollowerId(),
				follow.getSourceId())) {
			return -1;
		}
		getCurrentSession().save(follow);
		return 1;
	}

	@Override
	public int deleteFollow(int sourceType, int followerId, int sourceId) {
		// TODO Auto-generated method stub
		String hql = "delete Follow  where sourceType=? and  followerId=? and sourceId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, sourceType);
		query.setInteger(1, followerId);
		query.setInteger(2, sourceId);
		return query.executeUpdate();
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete Follow where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(Follow follow) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(follow.getId())) {
			return -1;
		}
		getCurrentSession().update(follow);
		return 1;
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Follow f where f.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFollowExistsByKey(int sourceType, int followerId,
			int sourceId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Follow f where f.sourceType =? and f.followerId=? and f.sourceId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, sourceType);
		query.setInteger(1, followerId);
		query.setInteger(2, sourceId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Follow> queryFollowListByFollowerId(int followerId) {
		// TODO Auto-generated method stub
		String hql = "from Follow as follow where follow.followerId = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, followerId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Follow> queryAllForTest() {
		// TODO Auto-generated method stub
		String hql = "from Follow";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}

	// 关注人 可以取消他已经关注的人/问题
	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Follow as f where f.followerId =? and f.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Follow> queryFollowListByFollowedUserId(int followedUserId) {
		// TODO Auto-generated method stub
		return queryFollowListBySourceId(followedUserId, Follow.FOLLOW_USER);
	}

	@Override
	public List<Follow> queryFollowListByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return queryFollowListBySourceId(questionId, Follow.FOLLOW_QUESTION);
	}

	@SuppressWarnings("unchecked")
	private List<Follow> queryFollowListBySourceId(int sourceId, int sourceType) {
		String hql = "from Follow as follow where follow.sourceId = ?and follow.sourceType=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, sourceId);
		query.setInteger(1, sourceType);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Follow> queryFollowListByFollowerIdByType(int followerId,
			int type) {
		// TODO Auto-generated method stub
		String hql = "from Follow as follow where follow.followerId = ? and follow.sourceType=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, followerId);
		query.setInteger(1, type);
		return query.list();
	}

}
