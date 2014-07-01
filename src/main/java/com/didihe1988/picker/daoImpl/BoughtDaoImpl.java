package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.BoughtDao;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Bought;

@Repository
@Transactional
public class BoughtDaoImpl implements BoughtDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addBought(Bought bought) {
		// TODO Auto-generated method stub
		if (isBoughtExists(bought)) {
			return -1;
		}
		getCurrentSession().save(bought);
		return 1;
	}

	@Override
	public int deleteBought(Bought bought) {
		// TODO Auto-generated method stub
		if (!isBoughtExists(bought)) {
			return -1;
		}
		getCurrentSession().delete(bought);
		return 1;
	}

	@Override
	public int updateBought(Bought bought) {
		// TODO Auto-generated method stub
		if (!isBoughtExists(bought)) {
			return -1;
		}
		getCurrentSession().update(bought);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bought> queryBoughtByBookId(int bookId) {
		// TODO Auto-generated method stub
		String hql = "from Bought as b where b.bookId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bought> queryBoughtByUserId(int userId) {
		// TODO Auto-generated method stub
		String hql = "from Bought as b where b.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		return query.list();
	}

	@Override
	public Boolean isBoughtExists(Bought bought) {
		// TODO Auto-generated method stub
		if(bought==null)
		{
			return false;
		}
		String hql = "select count(*) from Bought as b where b.userId = ? and b.bookId = ? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bought.getUserId());
		query.setInteger(1, bought.getBookId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Bought queryBoughtByUserIdAndBookId(int userId, int bookId) {
		// TODO Auto-generated method stub
		String hql = "from Bought as b where b.userId=? and b.bookId =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, bookId);
		// bought not eixsts
		if (query.list().size() == 0) {
			return null;
		} else {
			return (Bought)query.list().get(0);
		}
	}

}
