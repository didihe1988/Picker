package com.didihe1988.picker.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.AttachmentDao;
import com.didihe1988.picker.model.Attachment;

@Repository
@Transactional
public class AttachmentDaoImpl implements AttachmentDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Attachment queryModelById(int id) {
		// TODO Auto-generated method stub
		return (Attachment) getCurrentSession().get(Attachment.class, id);
	}

	@Override
	public int addModel(Attachment attachment) {
		// TODO Auto-generated method stub
		getCurrentSession().save(attachment);
		return 0;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(id)) {
			return -1;
		}
		String hql = "delete Attachment where id=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(Attachment attachment) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(attachment.getId())) {
			return -1;
		}
		getCurrentSession().update(attachment);
		return 1;
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Attachment as a where a.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int queryLatestAttachmentId() {
		// TODO Auto-generated method stub
		String hql = "select max(a.id) from Attachment as a";
		Query query = getCurrentSession().createQuery(hql);
		return (Integer) query.uniqueResult();
	}

}
