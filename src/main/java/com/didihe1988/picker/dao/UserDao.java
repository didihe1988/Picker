package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.User;

public interface UserDao {
	public Long getMatchCount(String username, String password);

	public User queryUserById(int id);

	public User queryUserByUserName(String username);

	public int updateUser(User user);

	public int addUser(User user);

	public int deleteUser(User user);

	public boolean isUserExists(User user);	
	
}
