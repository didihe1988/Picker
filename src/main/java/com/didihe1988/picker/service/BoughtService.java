package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Bought;

public interface BoughtService {
	public int addBought(Bought bought);

	public int updateBought(Bought bought);

	public int deleteBought(Bought bought);

	public List<Bought> getBoughtByBookId(int bookId);

	public List<Bought> getBoughtByUserId(int userId);

	public boolean isBoughtExistsByKey(int userId, int bookId);

	public Bought getBoughtByUserIdAndBookId(int userId, int bookId);
}
