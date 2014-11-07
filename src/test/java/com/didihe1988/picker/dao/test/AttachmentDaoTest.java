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

import com.didihe1988.picker.dao.AttachmentFeedDao;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.dp.AttachmentDp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttachmentDaoTest {
	@Autowired
	private AttachmentFeedDao attachmentFeedDao;
	
	@Test
	public void test0()
	{
		List<AttachmentFeed> list=attachmentFeedDao.queryAttachmentFeedsByBookId(3);
		assertNotNull(list);
		System.out.println(list);
	}
}
