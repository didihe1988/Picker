package com.didihe1988.picker.dao;

import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.User;

public interface UserDao extends NumOperation {
	public Long getMatchCount(String email, String password);

	public User queryUserById(int id);

	public User queryUserByEmail(String email);

	public int updateUser(User user);

	public int addUser(User user);

	public int deleteUser(User user);

	public boolean isUserExists(User user);

	public boolean isUserExistsById(int userId);

	public boolean isEmailExists(String email);

	public boolean isUsernameExists(String username);

}
