package com.didihe1988.picker.dao.impl;

import java.util.List;

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
	public Attachment queryAttachmentById(int id) {
		// TODO Auto-generated method stub
		return (Attachment) getCurrentSession().get(Attachment.class, id);
	}

	@Override
	public int addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		getCurrentSession().save(attachment);
		return 0;
	}

	@Override
	public int deleteAttachmentById(int id) {
		// TODO Auto-generated method stub
		if (!isAttachmentExistsById(id)) {
			return -1;
		}
		String hql = "delete Attachment where id=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		if (!isAttachmentExistsById(attachment.getId())) {
			return -1;
		}
		getCurrentSession().update(attachment);
		return 1;
	}

	@Override
	public boolean isAttachmentExistsById(int id) {
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
	public boolean isAttachmentExistsInCircle(String fileName, int circleId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Attachment as a where a.name =? and a.circleId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, fileName);
		query.setInteger(1, circleId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> queryAttachmentsByCircleId(int circleId) {
		// TODO Auto-generated method stub
		String hql = "from Attachment as a where a.circleId =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, circleId);
		return query.list();
	}

	@Override
	public int getLatestAttachmentByCircleId(int circleId) {
		// TODO Auto-generated method stub
		String hql = "select max(a.id) from Attachment as a where a.circleId= ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, circleId);
		return (Integer) query.uniqueResult();
	}

}
