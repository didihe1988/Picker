package com.didihe1988.picker.dao.interfaces;

public interface BaseDao<Model> {
	// public int add(Object object);
	public Model queryModelById(int id);

	public boolean isModelExistsById(int id);

	public int addModel(Model model);

	public int updateModel(Model model);

	public int deleteModelById(int id);
}
