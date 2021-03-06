package com.didihe1988.picker.dao.interfaces;

import java.util.List;

public interface PageRelatedOperation<T> {

	public List<T> queryModelListByPage(int bookId, int page);
	
	public List<T> queryModelListBetweenPage(int bookId,int startPage,int endPage);
}
