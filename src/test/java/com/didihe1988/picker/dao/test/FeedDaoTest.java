package com.didihe1988.picker.dao.test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedDaoTest {
	@Autowired
	private FeedDao feedDao;
	
	@Test
	public void test0()
	{
		List<Feed> list=feedDao.queryFeedListByUserId(1, Feed.TYPE_NOTE, 2);
		System.out.println(list.toString());
	}
}
