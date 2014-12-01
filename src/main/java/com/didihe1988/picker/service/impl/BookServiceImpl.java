package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return bookDao.queryBookById(id);
	}

	@Override
	public int getBookIdByISBN(String isbn) {
		// TODO Auto-generated method stub
		int result = bookDao.queryBookIdByISBN(isbn);
		if (result == -1) {
			return Status.NOT_EXISTS;
		}
		return result;
	}

	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		if (book == null) {
			return Status.NULLPOINTER;
		}
		int status = bookDao.addModel(book);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
		if (book == null) {
			return Status.NULLPOINTER;
		}
		int status = bookDao.deleteBook(book);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		if (book == null) {
			return Status.NULLPOINTER;
		}
		int status = bookDao.updateModel(book);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isBookExistsById(int id) {
		// TODO Auto-generated method stub
		return bookDao.isModelExistsById(id);
	}

	@Override
	public boolean isBookExistsByKey(String isbn) {
		// TODO Auto-generated method stub
		return bookDao.isBookExistsByKey(isbn);
	}

	@Override
	public int incrementQuestionNum(int id) {
		// TODO Auto-generated method stub
		if (getBookById(id) == null) {
			return Status.NOT_EXISTS;
		}
		int status = bookDao.incrementComment(id);
		// 这个应该怎样判断返回 测试一下判断hibernate的返回值
		return Status.SUCCESS;
	}

	@Override
	public int decrementQuestionNum(int id) {
		// TODO Auto-generated method stub
		if (getBookById(id) == null) {
			return Status.NOT_EXISTS;
		}
		int status = bookDao.decrementQuestionNum(id);
		// 这个应该怎样判断返回
		return Status.SUCCESS;
	}

	@Override
	public List<Book> search(String bookName) {
		// TODO Auto-generated method stub
		return bookDao.search(bookName);
	}

}
