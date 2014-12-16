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

import com.didihe1988.picker.common.DaoConstant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.HttpUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedServiceTest {
	@Autowired
	private FeedService feedService;

	@Test
	public void test0() {
		int bookId = 2;
		int userId = 1;
		int page = 3;
		Feed feed = new Feed(bookId, userId, "question title",
				"question content", "question brief", page, Feed.TYPE_QUESTION);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test1() {
		int bookId = 2;
		int userId = 1;
		int page = 3;
		Feed feed = new Feed(bookId, userId, "note title", "note content",
				"note brief", page, Feed.TYPE_NOTE);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test2() {
		List<Feed> list = feedService.getFeedListForBrowse(1);
		assertNotNull(list);
	}

	@Test
	public void test3() {
		Feed feed = feedService.getFeedById(8);
		assertNotNull(feed);
	}

	@Test
	public void test4() {
		List<NoteJson> list = feedService.getNoteJsons(1, 1);
		assertNotNull(list);
	}

	@Test
	public void test5() {
		Feed feed = feedService.getFeedById(4);
		assertSame(feed.getType(), Feed.TYPE_QUESTION);
	}

	@Test
	public void test6() {
		int id = 1;
		int userId = 1;
		List<FeedDp> list = feedService.getFeedDpListByBookId(id,
				Feed.TYPE_QUESTION, userId, DaoConstant.FeedPageOrder);
		System.out.println(list);
		assertNotNull(list);
	}

}
