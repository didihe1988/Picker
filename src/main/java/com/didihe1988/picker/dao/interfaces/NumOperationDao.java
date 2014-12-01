package com.didihe1988.picker.dao.interfaces;

public interface NumOperationDao<Model> extends BaseDao<Model> {
	public int incrementNum(String property, int id);

	public int decrementNum(String property, int id);
}
