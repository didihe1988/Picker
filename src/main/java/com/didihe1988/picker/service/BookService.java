package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Book;

public interface BookService {
	// public List<Book> findAllByUserId(int id);
	public Book findBookById(int id);
	public int getBookIdByISBN(String isbn);
}
