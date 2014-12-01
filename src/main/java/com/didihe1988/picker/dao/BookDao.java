package com.didihe1988.picker.dao;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.Book;

public interface BookDao extends BaseDao<Book>, SearchOperation<Book> {
	public Book queryBookById(int id);

	public int queryBookIdByISBN(String isbn);

	public int deleteBook(Book book);

	public boolean isBookExistsByKey(String isbn);

	public int incrementFollowNum(int bookId);

	public int incrementComment(int bookId);

	public int incrementQuestionNum(int id);

	public int decrementQuestionNum(int id);
}
