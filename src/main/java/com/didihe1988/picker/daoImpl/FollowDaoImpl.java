package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.validation.DeleteValidation;

@Repository
@Transactional
public class FollowDaoImpl implements FollowDao, DeleteValidation {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Follow queryFollowById(int id) {
		// TODO Auto-generated method stub
		return (Follow) getCurrentSession().get(Follow.class, id);
	}

	@Override
	public int addFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (isFollowExists(follow)) {
			return -1;
		}
		getCurrentSession().save(follow);
		return 1;
	}

	@Override
	public int deleteFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (!isFollowExists(follow)) {
			return -1;
		}
		getCurrentSession().delete(follow);
		return 1;
	}

	@Override
	public int updateFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (!isFollowExists(follow)) {
			return -1;
		}
		getCurrentSession().update(follow);
		return 1;
	}

	@Override
	public boolean isFollowExists(int id) {
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
	public boolean isFollowExists(Follow follow) {
		// TODO Auto-generated method stub
		if (follow == null) {
			return false;
		}
		String hql = "select count(*) from Follow f where f.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, follow.getId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Follow> queryFollowByFollowerId(int followerId) {
		// TODO Auto-generated method stub
		String hql = "from Follow as follow where follow.followerId = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, followerId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Follow> queryFollowByFollowedUserId(int followedUserId) {
		// TODO Auto-generated method stub
		String hql = "from Follow as follow where follow.sourceId = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, followedUserId);
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
	public boolean checkDeleteValidation(int ownerId, int objectId) {
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

}
