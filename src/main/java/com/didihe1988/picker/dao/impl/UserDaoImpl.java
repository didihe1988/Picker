package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.utils.DaoUtils;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Long getMatchCount(String email, String password) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User as u where u.email=? and u.password = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, email);
		query.setString(1, password);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User queryUserByEmail(String email) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.email=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, email);
		List<User> list = query.list();
		if (list.size() == 0) {
			return null;
		}
		return (User) list.get(0);
	}

	@Override
	public int updateModel(User user) {
		// TODO Auto-generated method stub
		if (!isUserExists(user)) {
			return -1;
		}
		getCurrentSession().update(user);
		return 1;
	}

	@Override
	public int addModel(User user) {
		// TODO Auto-generated method stub
		if (isUserExists(user)) {
			return -1;
		}
		getCurrentSession().save(user);
		return 1;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		if (!isUserExists(user)) {
			return -1;
		}
		getCurrentSession().delete(user);
		return 1;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete User where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public User queryModelById(int id) {
		// TODO Auto-generated method stub
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	public boolean isUserExists(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return false;
		}
		String hql = "select count(*) from User u where u.email =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, user.getEmail());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isModelExistsById(int userId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where u.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmailExists(String email) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where u.email =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, email);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where u.username =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, username);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update User as u set u." + property + "=u." + property
				+ "+1  where u.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update User as u set u." + property + "=u." + property
				+ "-1  where u.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> search(String string, boolean isLimited) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.username like ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, "%" + string + "%");
		if (isLimited) {
			DaoUtils.setLimitNum(query, Constant.DEFAULT_SEARCH_LIMITNUM);
		}
		return query.list();
	}

}
