package com.didihe1988.picker.service.impl;

import java.util.List;

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
	public Section getSectionByPage(int bookId, int sectionType, int page) {
		// TODO Auto-generated method stub
		return sectionDao.querySectionByPage(bookId, sectionType, page);
	}



	@Override
	public ChapterRange getChapterRangeByPage(int bookId,int sectionType,int page) {
		// TODO Auto-generated method stub
		Book book = bookDao.queryModelById(bookId);
		if ((book != null) && book.hasInventory()) {
			return ChapterRange.toChapterRange(getSectionByPage(bookId,sectionType,page));
		}
		return ChapterRange.toChapterRange(page, Constant.DEFAULT_PAGE_RAGE);
	}

	@Override
	public ChapterRange getChapterRangeByPage(Book book, int sectionType,int page) {
		// TODO Auto-generated method stub
		if ((book != null) && book.hasInventory()) {
			return ChapterRange.toChapterRange(getSectionByPage(book.getId(),sectionType,
					page));
		}
		return ChapterRange.toChapterRange(page, Constant.DEFAULT_PAGE_RAGE);
	}

	@Override
	public List<Section> getSectionsByBookId(int bookId) {
		// TODO Auto-generated method stub
		List<Section> chapters = sectionDao.querySectionsByType(bookId,
				Section.CHAPTER);
		for (Section chapter : chapters) {
			chapter.setSubSections(sectionDao.querySectionsBetweenPages(bookId,
					Section.SECTION, chapter.getStartPage(),
					chapter.getEndPage()));
		}
		return chapters;
	}

}
