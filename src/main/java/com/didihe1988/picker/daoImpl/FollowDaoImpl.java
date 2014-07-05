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

@Repository
@Transactional
public class FollowDaoImpl implements FollowDao {
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
	public int updateFollowSetIsCheckedReverse(int id) {
		if (!isFollowExists(id)) {
			return -1;
		}
		// 有没有sql语句直接把true设成false false设成true
		String hql = "update Follow as follow set follow.isChecked false";
		Query query = getCurrentSession().createQuery(hql);
		return query.executeUpdate();
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
	public List<Follow> queryUnckeckedFollowByFollowerId(int followerId) {
		String hql = "from Follow as follow where follow.followerId =? and follow.isChecked = false";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, followerId);
		return query.list();
	}

}
