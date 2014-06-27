package com.didihe1988.picker.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int getMatchCount(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.username=? and u.password = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);
		return query.list().size();
	}

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.username=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, username);
		return (User) query.list().get(0);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getCurrentSession().update(user);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserDaoImpl" + user.toString());
		// getCurrentSession().save(user);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		getCurrentSession().delete(user);
	}

}
