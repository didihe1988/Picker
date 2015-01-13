package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Section;

public interface SectionService {
	public Section getSectionByPage(int bookId,int sectionType, int page);
	
	public ChapterRange getChapterRangeByPage(int bookId,int sectionType,int page);
	
	public ChapterRange getChapterRangeByPage(Book book,int sectionType,int page);
	
	public List<Section> getSectionsByBookId(int bookId);
}
