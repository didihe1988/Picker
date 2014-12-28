package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Section;

public interface SectionDao {
	public Section querySectionByPage(int bookId,int page);
	
}
