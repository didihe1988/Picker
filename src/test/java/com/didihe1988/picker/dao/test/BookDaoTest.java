package com.didihe1988.picker.dao.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })

public class BookDaoTest {
	@Autowired
	private BookDao bookDao;

	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setBookName("The silence of lamps");
		book.setIsbn("123456");
		book.setPress("press01");
		bookDao.addBook(book);
	}

	@Test
	public void testQueryBookById() {
		Book book = bookDao.queryBookById(0);
		assertNotNull(book);
	}

	@Test
	public void testQueryBookIdByISBN() {
		int id = bookDao.queryBookIdByISBN("123456");
		assertSame(1, id);
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book();
		book.setBookName("The silence of lamps");
		book.setIsbn("123456");
		book.setPress("press02");
		bookDao.updateBook(book);
		Book book1 = bookDao.queryBookById(1);
		assertNotNull(book);
	}

	@Test
	public void testDeleteBook() {
		Book book = bookDao.queryBookById(1);
		bookDao.deleteBook(book);
	}

}
