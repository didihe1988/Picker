package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Section;
import com.didihe1988.picker.service.SectionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SectionServiceTest {
	@Autowired
	private SectionService sectionService;

	@Test
	public void test0() {
		int bookId = 1035;
		List<Section> sections = sectionService.getSectionsByBookId(bookId);
		assertNotNull(sections);
		System.out.println(sections);
	}

	@Test
	public void test1() {
		int bookId = 1035;
		ChapterRange range = sectionService.getChapterRangeByPage(bookId,
				Section.CHAPTER, 14);
		assertNotNull(range);
		System.out.println(range);
	}
}
