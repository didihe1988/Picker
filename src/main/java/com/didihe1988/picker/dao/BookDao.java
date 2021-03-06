package com.didihe1988.picker.dao;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.Book;

public interface BookDao extends BaseDao<Book>, SearchOperation<Book>,NumOperationDao<Book>{

	public int queryBookIdByISBN(String isbn);

	public int deleteBook(Book book);

	public boolean isBookExistsByKey(String isbn);

	public int incrementFollowNum(int bookId);

	public int incrementComment(int bookId);
}
