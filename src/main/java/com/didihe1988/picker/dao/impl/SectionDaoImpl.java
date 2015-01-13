package com.didihe1988.picker.dao.impl;

import java.util.List;

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
public class SectionDaoImpl implements SectionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Section querySectionByPage(int bookId, int type, int page) {
		// TODO Auto-generated method stub
		String hql = "from Section s where s.bookId = ? and type = ? and ? between s.startPage and s.endPage";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, type);
		query.setInteger(2, page);
		return (Section) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> querySectionsBetweenPages(int bookId, int type,
			int startPage, int endPage) {
		// TODO Auto-generated method stub
		String hql = "from Section s where s.bookId= ? and s.type = ? and s.startPage between ? and ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, type);
		query.setInteger(2, startPage);
		query.setInteger(3, endPage);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> querySectionsByType(int bookId, int type) {
		// TODO Auto-generated method stub
		String hql = "from Section s where s.bookId= ? and s.type = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		query.setInteger(1, type);
		return query.list();
	}

}
