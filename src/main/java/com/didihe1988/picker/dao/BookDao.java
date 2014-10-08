package com.didihe1988.picker.dao;

import com.didihe1988.picker.dao.daoInterface.SearchOperation;
import com.didihe1988.picker.model.Book;

public interface BookDao extends SearchOperation<Book> {
	public Book queryBookById(int id);

	public int queryBookIdByISBN(String isbn);

	public int addBook(Book book);

	public int deleteBook(Book book);

	public int updateBook(Book book);

	public boolean isBookExistsById(int id);

	public boolean isBookExistsByKey(String isbn);

	public int incrementFollowNum(int bookId);

	public int incrementComment(int bookId);

	public int incrementQuestionNum(int id);

	public int decrementQuestionNum(int id);
}
