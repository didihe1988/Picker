package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.didihe1988.picker.dao.BoughtDao;
import com.didihe1988.picker.model.Bought;

@Repository
public class BoughtDaoImpl implements BoughtDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addBought(Bought bought) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(bought);
		transaction.commit();
	}

	@Override
	public void deleteBought(Bought bought) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(bought);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BoughtDao> queryBoughtByUserId(int userId) {
		// TODO Auto-generated method stub
		String hql = "from Bought as b where b.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		return query.list();
	}
}
