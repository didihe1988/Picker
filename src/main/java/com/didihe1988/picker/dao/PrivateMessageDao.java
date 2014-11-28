package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.PrivateMessage;

public interface PrivateMessageDao {
	public PrivateMessage queryPrivateMessageById(int id);

	public int addPrivateMessage(PrivateMessage privateMessage);

	public int deletePrivateMessageById(int id);

	public int updatePrivateMessage(PrivateMessage privateMessage);

	public boolean isPrivateMessageExistsById(long id);

	public List<PrivateMessage> queryPrivateMessageByUserId(int userId);

	public List<PrivateMessage> queryPrivateMessageByDialogId(long dialogId);

	public int getLatestPrivateMessageIdByUserId(int id);
	
	/**
	 * @description return -1 if dialog not exists
	 */
	public long getDialogIdByUserId(int userId1, int userId2);

	public boolean checkOperateValidation(int userId, long dialogId);
}
