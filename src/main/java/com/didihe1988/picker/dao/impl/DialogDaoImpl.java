package com.didihe1988.picker.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.DialogDao;
import com.didihe1988.picker.model.Dialog;

@Repository
@Transactional
public class DialogDaoImpl implements DialogDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int incrementCount(long id) {
		// TODO Auto-generated method stub
		String hql = "update Dialog as d set d.count = d.count +1 where d.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementCount(long id) {
		// TODO Auto-generated method stub
		String hql = "update Dialog as d set d.count = d.count -1 where d.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		return query.executeUpdate();
	}

	@Override
	public Dialog queryDialogById(long id) {
		// TODO Auto-generated method stub
		return (Dialog) getCurrentSession().get(Dialog.class, id);
	}

	@Override
	public int addDialog(Dialog dialog) {
		// TODO Auto-generated method stub
		getCurrentSession().save(dialog);
		return 0;
	}

	@Override
	public int deleteDialogById(long id) {
		// TODO Auto-generated method stub
		if (!isDialogExistsById(id)) {
			return -1;
		}
		String hql = "delete Dialog where id=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		return query.executeUpdate();
	}

	@Override
	public boolean isDialogExistsById(long id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Dialog as d where d.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public long queryLatestDialogId() {
		// TODO Auto-generated method stub
		String hql = "select max(d.id) from Dialog as d";
		Query query = getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

}
