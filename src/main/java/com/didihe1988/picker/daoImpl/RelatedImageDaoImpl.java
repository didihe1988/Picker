package com.didihe1988.picker.daoImpl;

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

	@Override
	public int deleteRelatedImage(RelatedImage relatedImage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRelatedImage(RelatedImage relatedImage) {
		// TODO Auto-generated method stub
		return 0;
	}

}
