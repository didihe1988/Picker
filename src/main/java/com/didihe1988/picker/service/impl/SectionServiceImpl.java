package com.didihe1988.picker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.SectionDao;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Section;
import com.didihe1988.picker.service.SectionService;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private BookDao bookDao;

	@Override
	public Section getSectionByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		return sectionDao.querySectionByPage(bookId, page);
	}

	@Override
	public ChapterRange getChapterRangeByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		Book book = bookDao.queryModelById(bookId);
		if ((book != null) && book.hasInventory()) {
			return ChapterRange.toChapterRange(getSectionByPage(bookId, page));
		}
		return ChapterRange.toChapterRange(page, Constant.DEFAULT_PAGE_RAGE);
	}
	
	@Override
	public ChapterRange getChapterRangeByPage(Book book, int page) {
		// TODO Auto-generated method stub
		if ((book != null) && book.hasInventory()) {
			return ChapterRange.toChapterRange(getSectionByPage(book.getId(), page));
		}
		return ChapterRange.toChapterRange(page, Constant.DEFAULT_PAGE_RAGE);
	}
	
	
	
	

}
