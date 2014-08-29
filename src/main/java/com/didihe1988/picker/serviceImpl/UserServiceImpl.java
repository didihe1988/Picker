package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.UserDp;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean hasMatchUser(String email, String password) {
		// TODO Auto-generated method stub
		String passwordAfterMD5 = encryptByMD5(email, password);
		return userDao.getMatchCount(email, passwordAfterMD5) > 0;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.queryUserByEmail(email);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return Status.NULLPOINTER;
		}
		String passwordAfterMD5 = encryptByMD5(user);
		user.setPassword(passwordAfterMD5);
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
		return userDao.isUserExists(user);
	}

	@Override
	public boolean isEmailExists(String email) {
		// TODO Auto-generated method stub
		return userDao.isEmailExists(email);
	}

	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		return userDao.isUsernameExists(username);
	}

	private UserDp getUserDpByUser(User user) {
		return new UserDp(user);
	}

	@Override
	public UserDp getUserDpByUserId(int id) {
		// TODO Auto-generated method stub
		User user = userDao.queryUserById(id);
		return getUserDpByUser(user);
	}

	private String encryptByMD5(User user) {
		/*
		 * String passwordAfterMD5 =
		 * StringUtils.getMd5String(user.getPassword()) .substring(0, 6) +
		 * user.getUsername().substring(2);
		 */
		String passwordAfterMD5 = StringUtils.getMd5String(user.getPassword());
		return passwordAfterMD5;
	}

	private String encryptByMD5(String username, String password) {
		String passwordAfterMD5 = StringUtils.getMd5String(password).substring(
				0, 6)
				+ username.substring(2);
		return passwordAfterMD5;
	}

}
