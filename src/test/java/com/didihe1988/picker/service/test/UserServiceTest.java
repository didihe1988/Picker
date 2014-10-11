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
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void test0() {
		User user = new User("longze", "1073612209@qq.com", "didihe1988",
				"/resources/image/avatar/user_avatar4.jpg", "sun of beach");
		int status = userService.addUser(user);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * 改下密码，忘记之前的密码了
	 */

	@Test
	public void test1() {
		User user = userService.getUserById(3);
		// 不在updateUser中加密
		user.setPassword(encryptByMD5(user.getEmail(), "mini2440"));
		int status = userService.updateUser(user);
		assertSame(Status.SUCCESS, status);
	}

	private String encryptByMD5(String email, String password) {
		String passwordAfterMD5 = StringUtils.getMd5String(password);
		String emailAfterMD5 = StringUtils.getMd5String(email);
		return passwordAfterMD5 + emailAfterMD5.substring(0, 2);
	}

}
