package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.service.FeedService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedServiceTest {
	@Autowired
	private FeedService feedService;

	@Test
	public void test0() {
		Feed feed = new Feed(
				1,
				3,
				"testing...",
				"Provide simple toJson() and fromJson() methods to convert Java objects to JSON and vice-versa",
				66, Feed.TYPE_QUESTION);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test1() {
		Feed feed = new Feed(
				1,
				3,
				"Javadocs for the current Gson release",
				"Support arbitrarily complex objects (with deep inheritance hierarchies and extensive use of generic types",
				79, Feed.TYPE_NOTE);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test2() {
		List<Feed> list = feedService.getFeedListForBrowse(1);
		System.out.println(list);
	}

	@Test
	public void test3() {
		Feed feed = feedService.getFeedById(8);
		System.out.print(feed.getContent());
	}

}
