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

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.service.FavoriteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FavoriteServiceTest {
	@Autowired
	private FavoriteService favoriteService;

	/*
	 * test incrementNoteFavorite
	 */
	@Test
	public void test0() {
		int status = favoriteService.incrementNoteFavorite(1, 1);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test decrementNoteFavorite
	 */
	@Test
	public void test1() {
		int status = favoriteService.decrementNoteFavorite(1, 1);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test incrementAnswerFavorite
	 */
	@Test
	public void test2() {
		int status = favoriteService.incrementAnswerFavorite(1, 1);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test incrementQuestionFavorite
	 */
	@Test
	public void test3() {
		int status = favoriteService.incrementQuestionFavorite(1, 1);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test incrementCommentFavorite
	 */
	@Test
	public void test4() {
		int status = favoriteService.incrementCommentFavorite(1, 1);
		assertSame(Status.SUCCESS, status);
	}

}
