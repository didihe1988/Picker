package com.didihe1988.picker.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.model.Book;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Book queryBookById(int id) {
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
			return ((Book) query.list().get(0)).getId();
		}
	}

	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		// book exists
		if (queryBookIdByISBN(book.getIsbn()) > 0) {
			return -1;
		}
		getCurrentSession().save(book);
		return 1;

	}

	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
		if(!isBookExists(book))
		{
			return -1;
		}
		getCurrentSession().delete(book);
		return 1;
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		if(!isBookExists(book))
		{
			return -1;
		}
		getCurrentSession().update(book);
		return 1;
	}

	@Override
	public boolean isBookExists(Book book) {
		// TODO Auto-generated method s
		String hql = "select count(*) from Book b where b.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, book.getId());
		Long count = (Long)query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}
