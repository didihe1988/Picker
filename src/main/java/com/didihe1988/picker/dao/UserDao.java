package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.User;

public interface UserDao {
	public int getMatchCount(String username, String password);

	public User getUserByUserName(String username);

	public void updateUser(User user);

	public void addUser(User user);

	public void deleteUser(User user);
}
