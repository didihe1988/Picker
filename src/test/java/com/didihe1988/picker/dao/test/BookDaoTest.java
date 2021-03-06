package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoTest {
	@Autowired
	private BookDao bookDao;

	// testAddBook isbn repeat
	@Test
	public void test01() {
		Book book = new Book("x86", "9787121187995", "Li Zhong",
				"Electronics Industry", "/resources/image/book/book_image5.jpg");
		int status = bookDao.addModel(book);
		assertSame(1, status);
	}

	// testAddBook
	@Test
	public void test02() {
		Book book = new Book("lalala", "9787532734191", "dota", "press1988",
				"/resources/image/book/test_book_image.jpg");
		int status = bookDao.addModel(book);
		assertSame(1, status);
	}

	// testQueryBookById book not exists
	@Test
	public void test03() {
		// Book book = bookDao.
		assertSame(null, book);
	}

	// testQueryBookById
	@Test
	public void test04() {
		Book book = bookDao.queryBookById(5);
		System.out.println(book.toString());
		assertNotNull(book);
	}

	// testQueryBookIdByISBN isbn not exists
	@Test
	public void test05() {
		int status = bookDao.queryBookIdByISBN("123456789");
		assertSame(-1, status);
	}

	// testQueryBookIdByISBN
	@Test
	public void test06() {
		int id = bookDao.queryBookIdByISBN("123456");
		assertSame(true, (id > 0));
	}

	// testDeleteBook
	@Test
	public void test07() {
		Book book = bookDao.queryBookById(3);
		int status = bookDao.deleteBook(book);
		assertSame(1, status);
	}

	@Test
	public void test08() {
		Book book = bookDao.queryBookById(5);
		book.setId(20);
		int status = bookDao.deleteBook(book);
		assertSame(-1, status);
	}

	// testUpdateBook
	@Test
	public void test09() {

		try {
			for (int id = 9135; id <= 9293; id++) {
				Book book = bookDao.queryBookById(id);
				if (book != null) {
					book.setImageUrl("/resources/image/book/" + id
							+ "/lpic.jpg");
					bookDao.updateModel(book);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// testUpdateBook update will execute even no changes
	@Test
	public void test10() {
		Book book = bookDao.queryBookById(11);
		bookDao.updateModel(book);
	}

	// testUpdateBook update book which not exists
	@Test
	public void test11() {
		Book book = bookDao.queryBookById(11);
		book.setId(20);
		int status = bookDao.updateModel(book);
		assertSame(-1, status);
	}

	// testincrementFollowNum
	@Test
	public void test12() {
		int result = bookDao.incrementFollowNum(1);
		System.out.println(result);
	}

	// testIncrementCommentNum
	@Test
	public void test13() {
		int result = bookDao.incrementComment(1);
		System.out.println(result);
	}

	// test search
	@Test
	public void test14() {
		List<Book> list = bookDao.search("����ʶ;");
		assertNotNull(list);
		assertNotNull(list.get(0));
	}
}
