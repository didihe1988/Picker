package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Bought;

public interface BoughtDao {
	public int addBought(Bought bought);

	public int deleteBought(Bought bought);

	public int updateBought(Bought bought);

	public List<Bought> queryBoughtByUserId(int userId);

	public boolean isBoughtExistsByKey(int userId, int bookId);

	public List<Bought> queryBoughtByBookId(int bookId);

	public Bought queryBoughtByUserIdAndBookId(int userId, int bookId);
}
