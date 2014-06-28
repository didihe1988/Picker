package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.mapper.UserMapper;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.MD5Utils;

@Service

public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	UserDao userDao;

	@Override
	public boolean hasMatchUser(String username, String password) {
		// TODO Auto-generated method stub
		String passwordAfterMD5 = MD5Utils.getMd5String(username.substring(2))
				+ MD5Utils.getMd5String(password).substring(2);
		return userMapper.getMatchCount(username, passwordAfterMD5) > 0;
	}

	@Override
	public User findUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userMapper.queryUserByUserName(username);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String passwordAfterMD5 = MD5Utils.getMd5String(user.getPassword()).substring(0,6)+user.getUsername().substring(2);
		user.setPassword(passwordAfterMD5);
		System.out.println("UserSerivceImpl" + user.toString());
		userDao.addUser(user);
	}

}
