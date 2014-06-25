package com.didihe1988.picker.service;

import java.util.List;

public interface OwnBookService {
	public void addBook(int bookId, int userId);

	public void deleteBook(int bookId, int userId);

	public List<Integer> findAllByUserId(int userId);
}
