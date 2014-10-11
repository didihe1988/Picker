package com.didihe1988.picker.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.model.RelatedImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelatedImageDaoTest {

	@Autowired
	private RelatedImageDao relatedImageDao;

	@Test
	public void test0() {
		RelatedImage relatedImage=relatedImageDao.queryFirstRelatedImagesByKey(19, RelatedImage.QUESTION_IMAGE);
		System.out.println(relatedImage.toString());
	}
	
	@Test
	public void test1() {
		RelatedImage relatedImage=relatedImageDao.queryFirstRelatedImagesByKey(16, RelatedImage.NOTE_IMAGE);
		System.out.println(relatedImage.toString());
	}
}
