package com.didihe1988.picker.service;

import com.didihe1988.picker.model.Book;

public interface BookService{
	// public List<Book> findAllByUserId(int id);
	public Book findBookById(int id);

	public int getBookIdByISBN(String isbn);

	public int addBook(Book book);

	public int deleteBook(Book book);

	public int updateBook(Book book);

	public boolean isBookExists(Book book);

	public int incrementQuestionNum(int id);

	public int decrementQuestionNum(int id);
}
