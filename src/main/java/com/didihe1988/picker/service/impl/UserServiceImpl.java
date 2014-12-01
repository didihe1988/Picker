package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.UserDp;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FollowDao followDao;

	@Override
	public boolean hasMatchUser(String email, String password) {
		// TODO Auto-generated method stub
		System.out.println(email + " " + password);
		String passwordAfterEncrypt = encryptByMD5(email, password);
		System.out.println(passwordAfterEncrypt);
		return userDao.getMatchCount(email, passwordAfterEncrypt) > 0;
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
		// String passwordAfterEncrypt = encryptByMD5(user);
		// user.setPassword(passwordAfterEncrypt);
		int status = userDao.updateModel(user);
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
		String passwordAfterEncrypt = encryptByMD5(user);
		user.setPassword(passwordAfterEncrypt);
		int status = userDao.addModel(user);
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

	private UserDp getUserDpByUser(User user, boolean isFollow) {
		return new UserDp(user, isFollow);
	}

	private List<UserDp> getUserDpList(List<User> userList, int curUserId) {
		List<UserDp> list = new ArrayList<UserDp>();
		for (User user : userList) {
			UserDp userDp = getUserDpByUserId(user.getId(), curUserId);
			list.add(userDp);
		}
		return list;
	}

	/*
	 * @Override public UserDp getUserDpByUserId(int id) { // TODO
	 * Auto-generated method stub User user = userDao.queryUserById(id); return
	 * getUserDpByUser(user, isFollow); }
	 */

	@Override
	public UserDp getUserDpByUserId(int userId, int curUserId) {
		// TODO Auto-generated method stub
		User user = userDao.queryUserById(userId);
		return getUserDpByUser(user, followDao.isFollowExistsByKey(
				Follow.FOLLOW_USER, curUserId, userId));
	}

	/*
	 * @Override public UserDp getUserDpByUserId(int userId, boolean isFollow) {
	 * // TODO Auto-generated method stub User user =
	 * userDao.queryUserById(userId); return getUserDpByUser(user, isFollow); }
	 */

	@Override
	public boolean isUserExistsById(int userId) {
		// TODO Auto-generated method stub
		return userDao.isModelExistsById(userId);
	}

	private String encryptByMD5(User user) {
		return encryptByMD5(user.getEmail(), user.getPassword());
	}

	private String encryptByMD5(String email, String password) {
		String passwordAfterMD5 = StringUtils.getMd5String(password);
		String emailAfterMD5 = StringUtils.getMd5String(email);
		return passwordAfterMD5 + emailAfterMD5.substring(0, 2);
	}

	@Override
	public List<UserDp> search(String username, int curUserId) {
		// TODO Auto-generated method stub
		return getUserDpList(userDao.search(username), curUserId);
	}

	@Override
	public UserDp getUserDpByEmail(String email) {
		// TODO Auto-generated method stub
		return getUserDpByUser(getUserByEmail(email), false);
	}

	@Override
	public List<User> search(String username) {
		// TODO Auto-generated method stub
		return userDao.search(username);
	}

}
