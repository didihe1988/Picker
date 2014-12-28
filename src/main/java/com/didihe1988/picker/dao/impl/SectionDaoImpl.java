package com.didihe1988.picker.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.SectionDao;
import com.didihe1988.picker.model.Section;

@Repository
@Transactional
public class SectionDaoImpl implements SectionDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Section querySectionByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		String hql = "from Section s where s.bookId = ? and ? between s.startPage and s.endPage";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, page);
		return (Section)query.uniqueResult();
	}
	
	
	
	

	
	
	

}
