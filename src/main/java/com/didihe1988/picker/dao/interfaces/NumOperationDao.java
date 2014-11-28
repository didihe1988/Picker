package com.didihe1988.picker.dao.interfaces;

public interface NumOperationDao extends BaseDao{
	public int incrementNum(String property, int id);

	public int decrementNum(String property, int id);
}
