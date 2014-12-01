package com.didihe1988.picker.dao;

import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.User;

public interface UserDao extends NumOperationDao<User>, SearchOperation<User> {
	public Long getMatchCount(String email, String password);

	public User queryUserById(int id);

	public User queryUserByEmail(String email);

	public int deleteUser(User user);

	public boolean isUserExists(User user);

	public boolean isEmailExists(String email);

	public boolean isUsernameExists(String username);

}
