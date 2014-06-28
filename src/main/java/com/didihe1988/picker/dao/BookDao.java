package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Book;

public interface BookDao {
	public Book queryBookById(int id);

	public int queryBookIdByISBN(String isbn);

	public int addBook(Book book);

	public int deleteBook(Book book);

	public int updateBook(Book book);

	public boolean isBookExists(Book book);
}
