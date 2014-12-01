package com.didihe1988.picker.dao.interfaces;

public interface BaseDao<Model> {
	// public int add(Object object);
	public boolean isModelExistsById(int id);

	public int addModel(Model model);

	public int updateModel(Model model);
}
