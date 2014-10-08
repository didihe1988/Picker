package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.UserDp;

public interface UserService {
	public boolean hasMatchUser(String email, String password);

	public User getUserByEmail(String email);

	public User getUserById(int id);

	public int updateUser(User user);

	public int addUser(User user);

	public int deleteUser(User user);

	public boolean isUserExists(User user);

	public boolean isUserExistsById(int userId);

	public UserDp getUserDpByUserId(int userId, int curUserId);

	/**
	 * @description 用于register后获取用户自己的UserDp
	 */
	public UserDp getUserDpByEmail(String email);

	public boolean isEmailExists(String email);

	public boolean isUsernameExists(String username);

	public List<UserDp> search(String username, int curUserId);

	public List<User> search(String username);
}
