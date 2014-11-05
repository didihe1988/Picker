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

import com.didihe1988.picker.dao.AttachmentDao;
import com.didihe1988.picker.model.dp.AttachmentDp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttachmentDaoTest {
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Test
	public void test0()
	{
		List<AttachmentDp> list=attachmentDao.queryAttachmentDpsByBookId(1);
		assertNotNull(list);
		System.out.println(list);
	}
}
