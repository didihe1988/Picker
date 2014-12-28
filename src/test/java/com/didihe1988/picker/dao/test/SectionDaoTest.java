package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.SectionDao;
import com.didihe1988.picker.model.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SectionDaoTest {
	@Autowired
	private SectionDao sectionDao;

	@Test
	public void test0() {
		int bookId = 1;
		int page = 14;
		Section section = sectionDao.querySectionByPage(bookId, page);
		assertNotNull(section);
		assertSame(
				true,
				(page >= section.getStartPage())
						&& (page <= section.getEndPage()));
		System.out.println(section);
	}
	
	//test  page==chapter endPage
	@Test
	public void test1()
	{
		int bookId = 1;
		int page = 16;
		Section section = sectionDao.querySectionByPage(bookId, page);
		assertNotNull(section);
		assertSame(
				true,
				(page >= section.getStartPage())
						&& (page <= section.getEndPage()));
		System.out.println(section);
	}
}
