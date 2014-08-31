package com.didihe1988.picker.service;

import com.didihe1988.picker.model.Book;

public interface BookService {
	// public List<Book> findAllByUserId(int id);
	public Book getBookById(int id);

	public int getBookIdByISBN(String isbn);

	public int addBook(Book book);

	public int deleteBook(Book book);

	public int updateBook(Book book);

	public boolean isBookExistsById(int id);

	public boolean isBookExistsByKey(String isbn);

	public int incrementQuestionNum(int id);

	public int decrementQuestionNum(int id);
}
