package com.didihe1988.picker.service;

import com.didihe1988.picker.model.User;

public interface UserService {
	public boolean hasMatchUser(String username, String password);

	public User findUserByUserName(String username);

	public void updateUser(User user);
}
