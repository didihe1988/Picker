package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.model.Circle;

@Repository
@Transactional
public class CircleDaoImpl implements CircleDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @description:ownerId为establisherId objectId为id
	 */
	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Circle as c where c.establisherId =? and c.id=?";
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
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Circle as c set c." + property + "=c." + property
				+ "+1  where c.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Circle as c set c." + property + "=c." + property
				+ "-1 where c.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public Circle queryModelById(int id) {
		// TODO Auto-generated method stub
		return (Circle) getCurrentSession().get(Circle.class, id);
	}

	@Override
	public int addModel(Circle circle) {
		// TODO Auto-generated method stub
		if (isCircleExistsByName(circle.getName())) {
			return -1;
		}
		getCurrentSession().save(circle);
		return 1;
	}

	@Override
	public int deleteCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(circle.getId())) {
			return -1;
		}
		getCurrentSession().delete(circle);
		return 1;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete Circle where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(Circle circle) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(circle.getId())) {
			return -1;
		}
		getCurrentSession().update(circle);
		return 1;
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Circle as c where c.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCircleExistsByName(String name) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Circle as c where c.name = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, name);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circle> queryCircleListByEstablisherId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Circle as c where c.establisherId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@Override
	public boolean isEstablisherOfCircle(int userId, int circleId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Circle as c where c.establisherId = ? and c.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, circleId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int queryLatestCircleIdByEstablisherId(int id) {
		// TODO Auto-generated method stub
		String hql = "select max(c.id) from Circle as c where c.establisherId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Integer) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circle> search(String name) {
		// TODO Auto-generated method stub
		String hql = "from Circle as c where c.name like ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, "%" + name + "%");
		return query.list();
	}

}
