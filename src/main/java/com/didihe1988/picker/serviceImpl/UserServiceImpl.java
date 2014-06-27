package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didihe1988.picker.mapper.UserMapper;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean hasMatchUser(String username, String password) {
		// TODO Auto-generated method stub
		String passwordAfterMD5 = MD5Utils.getMd5String(username.substring(2))
				+ MD5Utils.getMd5String(password);
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

}
