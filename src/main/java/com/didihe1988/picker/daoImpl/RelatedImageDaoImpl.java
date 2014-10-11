package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.model.RelatedImage;

@Repository
@Transactional
public class RelatedImageDaoImpl implements RelatedImageDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addRelatedImage(RelatedImage relatedImage) {
		// TODO Auto-generated method stub
		return (Integer) getCurrentSession().save(relatedImage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelatedImage> queryRelatedImagesByKey(int relatedId, int type) {
		// TODO Auto-generated method stub
		String hql = "from RelatedImage as r where r.relatedId=? and r.type=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, relatedId);
		query.setInteger(1, type);
		return query.list();
	}

	@Override
	public RelatedImage queryFirstRelatedImagesByKey(int relatedId, int type) {
		// TODO Auto-generated method stub
		String hql = "from RelatedImage as r where r.relatedId=? and r.type=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, relatedId);
		query.setInteger(1, type);
		query.setMaxResults(1);
		return (RelatedImage) query.uniqueResult();
	}

}
