package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean hasMatchUser(String username, String password) {
		// TODO Auto-generated method stub
		String passwordAfterMD5 = encryptByMD5(username, password);
		return userDao.getMatchCount(username, passwordAfterMD5) > 0;
	}

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.queryUserByUserName(username);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return Status.NULLPOINTER;
		}
		int status = userDao.updateUser(user);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return Status.NULLPOINTER;
		}
		String passwordAfterMD5 = encryptByMD5(user);
		user.setPassword(passwordAfterMD5);
		int status = userDao.addUser(user);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(id);
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return Status.NULLPOINTER;
		}
		int status = userDao.deleteUser(user);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isUserExists(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	private String encryptByMD5(User user) {
		String passwordAfterMD5 = MD5Utils.getMd5String(user.getPassword())
				.substring(0, 6) + user.getUsername().substring(2);
		return passwordAfterMD5;
	}

	private String encryptByMD5(String username, String password) {
		String passwordAfterMD5 = MD5Utils.getMd5String(password).substring(0,
				6)
				+ username.substring(2);
		return passwordAfterMD5;
	}

}
