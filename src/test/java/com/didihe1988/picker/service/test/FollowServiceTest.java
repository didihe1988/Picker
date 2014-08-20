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
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.service.FollowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FollowServiceTest {
	@Autowired
	private FollowService followService;

	/*
	 * test addQuestionFollow
	 */
	@Test
	public void test0() {
		Follow follow = new Follow(Follow.FOLLOW_QUESTION, 2, 1);
		int status = followService.addFollow(follow);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test1() {
		Follow follow = new Follow(Follow.FOLLOW_USER, 2, 1);
		int status = followService.addFollow(follow);
		assertSame(Status.SUCCESS, status);
	}
	
	
	
	
}
