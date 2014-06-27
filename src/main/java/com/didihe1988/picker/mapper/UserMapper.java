package com.didihe1988.picker.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.utils.MD5Utils;

@Repository
public class UserMapper {
	private static List<User> userList;

	public UserMapper() {
		// TODO Auto-generated constructor stub
		if (userList == null) {
			userList = new ArrayList<User>();
			String username = "didihe1988";
			String password = "mini2440";
			String passwordAfterMD5 = MD5Utils.getMd5String(username
					.substring(2)) + MD5Utils.getMd5String(password);
			userList.add(new User(0, username, passwordAfterMD5, new Date()));
		}
	}

	public int getMatchCount(String username, String password) {
		User user = userList.get(0);
		if (username.equals(user.getUsername())
				&& (password.equals(user.getPassword()))) {
			return 1;
		} else {
			return 0;
		}
	}

	public User queryUserByUserName(String username) {
		User user = userList.get(0);
		if (username.equals(user.getUsername())) {
			return user;
		} else {
			return null;
		}
	}

	public void updateUser(User user) {
		System.out.println(user.toString());
	}
}
