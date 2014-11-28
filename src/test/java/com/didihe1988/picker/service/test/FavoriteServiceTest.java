package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Favorite;
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
		int status = favoriteService.incModelFavorite(1, 4,
				Favorite.FAVORITE_NOTE);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test decrementNoteFavorite
	 */
	@Test
	public void test1() {
		int status = favoriteService.decModelFavorite(1, 4,
				Favorite.FAVORITE_NOTE);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test incrementAnswerFavorite
	 */
	@Test
	public void test2() {
		int status = favoriteService.incModelFavorite(1, 4,
				Favorite.FAVORITE_ANSWER);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test decrementAnswerFavorite
	 */
	@Test
	public void test3() {
		int status = favoriteService.decModelFavorite(1, 4,
				Favorite.FAVORITE_ANSWER);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test incrementCommentFavorite
	 */
	@Test
	public void test4() {
		int status = favoriteService.incModelFavorite(1, 4,
				Favorite.FAVORITE_COMMENT);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test decrementCommentFavorite
	 */
	@Test
	public void test5() {
		int status = favoriteService.decModelFavorite(1, 4,
				Favorite.FAVORITE_COMMENT);
		assertSame(Status.SUCCESS, status);
	}

}
