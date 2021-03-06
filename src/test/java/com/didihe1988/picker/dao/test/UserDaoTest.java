package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
	@Autowired
	UserDao userDao;

	// testAddUser
	@Test
	public void test01() {
		/*
		 * User user = new User(); user.setUsername("didihe1834"); String
		 * passwordAfterMd5 = encryptByMD5(user.getUsername(), "lalala");
		 * user.setPassword(passwordAfterMd5); user.setLastVisit(new Date());
		 */
		/*
		User user = new User("didihe1988", "1075619206@qq.com", encryptByMD5(
				"nanshu", "lalala"),
				"/resources/image/avatar/test_user_avatar.jpg");*/
		User user=new User("didihe1988", "fefef", "fefef", "fef");
		userDao.addModel(user);
	}

	// testAddUser id repeat
	@Test
	public void test02() {
		User user = new User();
		user.setId(1);
		user.setUsername("shaonian");
		user.setPassword("123456");
		user.setLastVisit(new Date());
		int status = userDao.addModel(user);
		assertSame(-1, status);
	}

	// testQueryUserById
	@Test
	public void test03() {
		User user = userDao.queryModelById(1);
		assertNotNull(user);
	}

	// testQueryUserById id not exists
	@Test
	public void test04() {
		User user = userDao.queryModelById(20);
		assertSame(null, user);
	}

	// testDeleteUser user not exists
	@Test
	public void test05() {
		User user = new User();
		user.setId(20);
		int status = userDao.deleteUser(user);
		assertSame(-1, status);
	}

	// testDeleteUser
	@Test
	public void test06() {
		User user = userDao.queryModelById(4);
		int status = userDao.deleteUser(user);
		assertSame(1, status);
	}

	// testQueryUserByUserName
	@Test
	public void test07() {
		User user = userDao.queryUserByUserName("shaonian");
		assertNotNull(user);
		assertSame(5, user.getId());
	}

	// testIsUserExists
	@Test
	public void test08() {
		User user = new User();
		user.setId(5);
		boolean isUserExists = userDao.isCircleMemberExists(user);
		assertSame(true, isUserExists);
	}

	// testIsUserExists user not exists
	@Test
	public void test09() {
		User user = new User();
		user.setId(20);
		boolean isUserExists = userDao.isCircleMemberExists(user);
		assertSame(false, isUserExists);
	}

	// testGetMatchCount
	@Test
	public void test10() {
		long count = userDao.getMatchCount("shaonian", "123456");
		assertSame((long) 2, count);
	}

	// testUpdateBook
	@Test
	public void test11() {
		User user = userDao.queryModelById(5);
		user.setPassword("lalala");
		userDao.updateModel(user);
	}

	// testUpdateBook book not exists
	@Test
	public void test12() {
		User user = new User();
		user.setId(20);
		int status = userDao.updateModel(user);
		assertSame(-1, status);
	}

	private String encryptByMD5(String username, String password) {
		String passwordAfterMD5 = StringUtils.getMd5String(password).substring(
				0, 6)
				+ username.substring(2);
		return passwordAfterMD5;
	}

}
