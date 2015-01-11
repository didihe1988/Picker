package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.dao.AttachmentFeedDao;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.utils.DaoUtils;

@Repository
@Transactional
public class AttachmentFeedDaoImpl implements AttachmentFeedDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public AttachmentFeed queryModelById(int id) {
		// TODO Auto-generated method stub
		return (AttachmentFeed) getCurrentSession().get(AttachmentFeed.class,
				id);
	}

	@Override
	public int addModel(AttachmentFeed attachmentFeed) {
		// TODO Auto-generated method stub
		getCurrentSession().save(attachmentFeed);
		return 0;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(id)) {
			return -1;
		}
		String hql = "delete AttachmentFeed where id=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(AttachmentFeed attachmentFeed) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(attachmentFeed.getId())) {
			return -1;
		}
		getCurrentSession().update(attachmentFeed);
		return 1;
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from AttachmentFeed as a where a.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	/*
	 * ---BookId related---
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeed> queryAttachmentFeedsByBookId(int bookId) {
		// TODO Auto-generated method stub
		String hql = "from AttachmentFeed as a where a.bookId =? order by a.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeedDp> queryAttachmentFeedDpsByBookId(int bookId) {
		String hql = "select new com.didihe1988.picker.model.display.AttachmentFeedDp(a,u.username,u.avatarUrl) from AttachmentFeed a ,User u where a.bookId=? and a.userId = u.id order by a.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeedDp> queryAttachmentFeedDpsByBookId(int bookId,
			String order) {
		// TODO Auto-generated method stub
		String hql = "select new com.didihe1988.picker.model.display.AttachmentFeedDp(a,u.username,u.avatarUrl) from AttachmentFeed a ,User u where a.bookId=? and a.userId = u.id "
				+ order;
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeedDp> queryLimitedAttachmentFeedDpsByBookId(
			int bookId, int limit) {
		// TODO Auto-generated method stub
		String hql = "select new com.didihe1988.picker.model.display.AttachmentFeedDp(a,u.username,u.avatarUrl) from AttachmentFeed a ,User u where a.bookId=? and a.userId = u.id order by a.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		DaoUtils.setLimit(query, limit);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeedDp> queryLimitedAttachmentFeedDpsByBookId(
			int bookId, int limit, String order) {
		// TODO Auto-generated method stub
		String hql = "select new com.didihe1988.picker.model.display.AttachmentFeedDp(a,u.username,u.avatarUrl) from AttachmentFeed a ,User u where a.bookId=? and a.userId = u.id"
				+ order;
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		DaoUtils.setLimit(query, limit);
		return query.list();
	}

	@Override
	public int getLatestAttachmentFeedByBookId(int bookId) {
		// TODO Auto-generated method stub
		String hql = "select max(a.id) from AttachmentFeed as a where a.bookId= ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return (Integer) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeed> queryModelListByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		String hql = "from AttachmentFeed as a where a.bookId =? and a.page=? order by a.id";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, page);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentFeed> queryModelListBetweenPage(int bookId,
			int startPage, int endPage) {
		// TODO Auto-generated method stub
		String hql = "from AttachmentFeed as a where a.bookId =? and a.page between ? and ? order by a.page,a.id asc";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, startPage);
		query.setInteger(2, endPage);
		return query.list();
	}
	
	
	
	

}
