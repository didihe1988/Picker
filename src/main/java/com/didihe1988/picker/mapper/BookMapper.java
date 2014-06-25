package com.didihe1988.picker.mapper;

import java.util.ArrayList;
import java.util.List;

import com.didihe1988.picker.model.Book;

public class BookMapper {
	/*
	 * public List<Book> queryBookByUserId(int id) { List<Book> bookList = new
	 * ArrayList<Book>(); for (int i = 0; i < 3; i++) { Book book = new Book(i,
	 * "bookName" + i, "isbn" + i); bookList.add(book); } return bookList; }
	 */
	private static List<Book> bookList;

	public BookMapper() {
		if (bookList == null) {
			bookList.add(new Book(0, "lalala", "isbn" + 1, "press"));
			bookList.add(new Book(1, "jinpingmei", "isbn" + 2, "press"));
			bookList.add(new Book(2, "weijifen", "isbn" + 3, "press"));
			bookList.add(new Book(3, "dawu", "isbn" + 4, "press"));
		}
	}

	public Book queryBookById(int id) {
		Book book = null;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getId() == id) {
				book = bookList.get(i);
			}
		}
		return book;
	}

	public int queryBookIdByISBN(String isbn) {
		int id = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getIsbn().equals(isbn)) {
				id = bookList.get(i).getId();
			}
		}
		return id;
	}
}
