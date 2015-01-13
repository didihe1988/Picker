package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Section;

public interface SectionDao {
	public Section querySectionByPage(int bookId,int type,int page);

	public List<Section> querySectionsBetweenPages(int bookId, int type,
			int startPage, int endPage);

	public List<Section> querySectionsByType(int bookId, int type);

}
