package com.didihe1988.picker.service;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.UserDp;

public interface UserService {
	public boolean hasMatchUser(String email, String password);

	public User getUserByEmail(String email);

	public User getUserById(int id);

	public int updateUser(User user);

	public int addUser(User user);

	public int deleteUser(User user);

	public boolean isUserExists(User user);

	public UserDp getUserDpByUserId(int id);

	public boolean isEmailExists(String email);

	public boolean isUsernameExists(String username);
}
