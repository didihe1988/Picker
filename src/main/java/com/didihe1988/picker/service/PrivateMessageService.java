package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.PrivateMessage;

public interface PrivateMessageService {
	public PrivateMessage getPrivateMessageById(int id);

	public int addPrivateMessage(PrivateMessage privateMessage);

	public int deletePrivateMessageById(int id);

	public int updatePrivateMessage(PrivateMessage privateMessage);

	public boolean isPrivateMessageExistsById(int id);

	public List<PrivateMessage> getPrivateMessageByUserId(int userId);

	public int getLatestPrivateMessageIdByUserId(int id);

	public long getDialogIdByUserId(int userId1, int userId2);
}
