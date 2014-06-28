package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Bought;

public interface BoughtDao {
	public void addBought(Bought bought);

	public void deleteBought(Bought bought);

	public List<BoughtDao> queryBoughtByUserId(int userId);
}
