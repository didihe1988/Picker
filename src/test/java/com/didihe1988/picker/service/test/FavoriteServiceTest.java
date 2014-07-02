package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;



import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.service.FavoriteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FavoriteServiceTest {
	@Autowired
	private FavoriteService favoriteService;
	
	//testaddCommentFavorite
	@Test 
	public void test0() 
	{
		//favoriteService.addCommentFavorite(1);
	}
}
