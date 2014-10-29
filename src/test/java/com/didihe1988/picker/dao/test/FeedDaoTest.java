package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedDaoTest {
	@Autowired
	private FeedDao feedDao;

	@Test
	public void test0() {
		List<Feed> list = feedDao.queryFeedListByUserId(1, Feed.TYPE_NOTE, 2);
		System.out.println(list.toString());
	}

	@Test
	public void test1() {
		List<Feed> list = feedDao.queryFeedListByUserId(1, Feed.TYPE_NOTE);
		System.out.println(list.size());
	}

	/*
	 * ≤‚ ‘∂‡±Ì≤È—Ø
	 */
	@Test
	public void test2() {
		List<FeedDp> list = feedDao.queryFeedDpListByBookId(1,
				Feed.TYPE_QUESTION);
		assertNotNull(list);
		for (FeedDp feed : list) {
			System.out.println(feed.toString());
		}
	}
}
