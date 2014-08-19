package com.didihe1988.picker.daoImpl;

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
	public boolean checkDeleteValidation(int ownerId, int objectId) {
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
				+ "+1 and where a.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Circle as c set c." + property + "=c." + property
				+ "-1 and where c.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public Circle queryCircleById(int id) {
		// TODO Auto-generated method stub
		return (Circle) getCurrentSession().get(Circle.class, id);
	}

	@Override
	public int addCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (isCircleExists(circle)) {
			return -1;
		}
		getCurrentSession().save(circle);
		return 1;
	}

	@Override
	public int deleteCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (!isCircleExists(circle)) {
			return -1;
		}
		getCurrentSession().delete(circle);
		return 1;
	}

	@Override
	public int updateCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (!isCircleExists(circle)) {
			return -1;
		}
		getCurrentSession().update(circle);
		return 1;
	}

	// 根据标签名不能重复判断 可能需要改
	@Override
	public boolean isCircleExists(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return false;
		}

		String hql = "select count(*) from Circle as c where c.name = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, circle.getName());
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

}