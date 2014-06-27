package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didihe1988.picker.mapper.BookMapper;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;

	@Override
	public Book findBookById(int id) {
		// TODO Auto-generated method stub
		return bookMapper.queryBookById(id);
	}

	@Override
	public int getBookIdByISBN(String isbn) {
		// TODO Auto-generated method stub
		return bookMapper.queryBookIdByISBN(isbn);
	}

	/*
	 * @Override public List<Book> findAllByUserId(int id) { // TODO
	 * Auto-generated method stub return bookMapper.queryBookByUserId(id); }
	 */

}
