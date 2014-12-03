package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.model.Follow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FollowDaoTest {
	@Autowired
	private FollowDao followDao;

	@Test
	public void test01() {
		Follow follow = new Follow();
		follow.setFollowerId(1);
		follow.setSourceType(Follow.FOLLOW_USER);
		follow.setSourceId(2);
		int status = followDao.addModel(follow);
		assertSame(1, status);
	}

	@Test
	public void test02() {
		Follow follow = followDao.queryModelById(1);
		System.out.println(follow.toString());
		assertNotNull(follow);
	}

	@Test
	public void test03() {
		Follow follow = followDao.queryModelById(1);
		followDao.updateModel(follow);
	}

	@Test
	public void test04() {
		List<Follow> list = followDao.queryFollowByFollowerId(1);
		assertNotNull(list);
		System.out.println(list);
	}


}
