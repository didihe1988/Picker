package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Book;

public interface BookDao {
	public Book queryBookById(int id);

	public int queryBookIdByISBN(String isbn);

	public void addBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book);
}
