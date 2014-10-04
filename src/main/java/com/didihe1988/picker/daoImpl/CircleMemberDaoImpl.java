package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.CircleMemberDao;
import com.didihe1988.picker.model.CircleMember;

@Repository
@Transactional
public class CircleMemberDaoImpl implements CircleMemberDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public CircleMember queryCircleMemberById(int id) {
		// TODO Auto-generated method stub
		return (CircleMember) getCurrentSession().get(CircleMember.class, id);
	}

	/*
	 * @Override public CircleMember queryCircleMemberByKey(int userId, int
	 * circleId) { // TODO Auto-generated method stub String hql =
	 * "from CircleMember as c where c.memberId =? and c.circleId=?"; Query
	 * query = getCurrentSession().createQuery(hql); query.setInteger(0,
	 * userId); query.setInteger(1, circleId); return (CircleMember)
	 * query.uniqueResult(); }
	 */

	@Override
	public int addCircleMember(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (isCircleMemberExists(circleMember)) {
			return -1;
		}
		getCurrentSession().save(circleMember);
		return 1;
	}

	@Override
	public int deleteCircleMember(int userId, int circleId) {
		// TODO Auto-generated method stub
		String hql = "delete CircleMember where memberId=? and circleId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, circleId);
		return query.executeUpdate();

	}

	@Override
	public int updateCircleMember(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (!isCircleMemberExistsById(circleMember.getId())) {
			return -1;
		}
		getCurrentSession().update(circleMember);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleMember> queryCircleMemberListByCircleId(int id) {
		// TODO Auto-generated method stub
		String hql = "from CircleMember as c where c.circleId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleMember> queryCircleMemberListByMemberId(int id) {
		// TODO Auto-generated method stub
		String hql = "from CircleMember as c where c.memberId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@Override
	public boolean isCircleMemberExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CircleMember as c where c.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCircleMemberExists(CircleMember circleMember) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CircleMember as c where c.circleId = ? and c.memberId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, circleMember.getCircleId());
		query.setInteger(1, circleMember.getMemberId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @description 本人可以删除自己已加入的圈子
	 */
	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CircleMember as c where c.memberId =? and c.id=?";
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
	public boolean isUserInCircle(int userId, int circleId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CircleMember as c where c.circleId = ? and c.memberId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, circleId);
		query.setInteger(1, userId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}
