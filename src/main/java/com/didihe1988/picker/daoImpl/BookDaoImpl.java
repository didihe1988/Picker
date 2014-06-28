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
		//Session session = getCurrentSession();
		//Transaction transaction = session.getTransaction();
		Book book = (Book) getCurrentSession().get(Book.class, id);
		//transaction.commit();
		return book;
	}

	@Override
	public int queryBookIdByISBN(String isbn) {
		// TODO Auto-generated method stub
		String hql = "from Book as b where b.isbn=?";
		Session session = getCurrentSession();
		Transaction transaction = session.getTransaction();
		Query query = session.createQuery(hql);
		query.setString(0, isbn);
		int id = ((Book) query.list().get(0)).getId();
		transaction.commit();
		return id;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		/*
		 * Session session = getCurrentSession(); Transaction transaction =
		 * session.beginTransaction(); session.save(book); transaction.commit();
		 */
		getCurrentSession().save(book);
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(book);
		transaction.commit();
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(book);
		transaction.commit();
	}

}
