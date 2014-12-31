package com.didihe1988.picker.service;

import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Section;

public interface SectionService {
	public Section getSectionByPage(int bookId, int page);
	
	public ChapterRange getChapterRangeByPage(int bookId,int page);
	
	public ChapterRange getChapterRangeByPage(Book book,int page);
}
