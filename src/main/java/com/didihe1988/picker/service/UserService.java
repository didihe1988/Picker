package com.didihe1988.picker.service;

import com.didihe1988.picker.model.User;

public interface UserService {
	public boolean hasMatchUser(String username, String password);

	public User getUserByUserName(String username);

	public User getUserById(int id);

	public int updateUser(User user);

	public int addUser(User user);

	public int deleteUser(User user);

	public boolean isUserExists(User user);
}
