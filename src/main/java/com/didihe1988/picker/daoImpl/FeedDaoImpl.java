package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.model.Feed;

@Repository
@Transactional
public class FeedDaoImpl implements FeedDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Feed as f where f.userId =? and f.id=?";
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
		String hql = "update Feed as f set f." + property + "=f." + property
				+ "+1 where f.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Feed as f set f." + property + "=f." + property
				+ "-1 where f.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public Feed queryFeedById(int id) {
		// TODO Auto-generated method stub
		return (Feed) getCurrentSession().get(Feed.class, id);
	}

	@Override
	public int addFeed(Feed feed) {
		// TODO Auto-generated method stub
		return (Integer) getCurrentSession().save(feed);
	}

	@Override
	public int deleteFeed(Feed feed) {
		// TODO Auto-generated method stub
		if (!isFeedExistsById(feed.getId())) {
			return -1;
		}
		getCurrentSession().delete(feed);
		return 1;
	}

	@Override
	public int deleteFeedById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete Feed where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateFeed(Feed feed) {
		// TODO Auto-generated method stub
		if (!isFeedExistsById(feed.getId())) {
			return -1;
		}
		getCurrentSession().update(feed);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> queryFeedListByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		String hql = "from Feed as f where f.bookId=?";
		addTypeConstrain(hql, type);
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> queryFeedListByUserId(int userId, int type) {
		// TODO Auto-generated method stub
		String hql = "from Feed as f where f.userId=?";
		addTypeConstrain(hql, type);
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		return query.list();
	}

	@Override
	public int getLatestFeedByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		String hql = "select max(f.id) from Feed as f where f.bookId=?";
		addTypeConstrain(hql, type);
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return (Integer) query.uniqueResult();
	}

	private void addTypeConstrain(String hql, int type) {
		if (type == Feed.TYPE_NOTE || type == Feed.TYPE_QUESTION) {
			hql += " and f.type =" + type;
		}
	}

	@Override
	public boolean isFeedExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Feed as f where f.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}