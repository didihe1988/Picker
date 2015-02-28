package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.utils.DaoUtils;

@Repository
@Transactional
public class BookDaoImpl implements BookDao{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Book queryModelById(int id) {
		// TODO Auto-generated method stub
		Book book = (Book) getCurrentSession().get(Book.class, id);
		return book;
	}

	@Override
	public int queryBookIdByISBN(String isbn) {
		// TODO Auto-generated method stub
		String hql = "from Book as b where b.isbn=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, isbn);
		// isbn not eixsts
		if (query.list().size() == 0) {
			return -1;
		} else {
			if (query.list().get(0) == null) {
				return -1;
			}
			return ((Book) query.list().get(0)).getId();
		}
	}

	@Override
	public int addModel(Book book) {
		// TODO Auto-generated method stub
		// book exists
		if (isBookExistsByKey(book.getIsbn())) {
			return -1;
		}
		getCurrentSession().save(book);
		return 1;

	}

	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(book.getId())) {
			return -1;
		}
		getCurrentSession().delete(book);
		return 1;
	}

	@Override
	public int deleteModelById(int id) {
		// TODO Auto-generated method stub
		String hql = "delete Book where id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updateModel(Book book) {
		// TODO Auto-generated method stub
		if (!isModelExistsById(book.getId())) {
			return -1;
		}
		getCurrentSession().update(book);
		return 1;
	}

	@Override
	public boolean isModelExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Book b where b.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBookExistsByKey(String isbn) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Book b where b.isbn = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, isbn);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Book as b set b." + property + "=b." + property
				+ "+1 where b.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Book as b set b." + property + "=b." + property
				+ "-1 where b.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int incrementFollowNum(int bookId) {
		// TODO Auto-generated method stub
		String hql = "update Book as book set book.followNum =book.followNum+1 where book.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.executeUpdate();
	}

	@Override
	public int incrementComment(int bookId) {
		// TODO Auto-generated method stub
		String hql = "update Book as book set book.commentNum =book.commentNum+1 where book.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, bookId);
		return query.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Book> search(String string, boolean isLimited) {
		// TODO Auto-generated method stub
		String hql = "from Book as b where b.bookName like ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, "%" + string + "%");
		if (isLimited) {
			DaoUtils.setLimitNum(query, Constant.DEFAULT_SEARCH_LIMITNUM);
		}
		return query.list();
	}

}
