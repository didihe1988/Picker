package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.PrivateMessage;
import com.didihe1988.picker.model.dp.PrivateMessageDp;

public interface PrivateMessageService {
	public PrivateMessage getPrivateMessageById(int id);

	public int addPrivateMessage(PrivateMessage privateMessage);

	public int deletePrivateMessageById(int id);

	public int updatePrivateMessage(PrivateMessage privateMessage);

	public boolean isPrivateMessageExistsById(int id);

	public List<PrivateMessage> getPrivateMessageByUserId(int userId);

	public List<PrivateMessage> getPrivateMessageByDialogId(long dialogId);

	public List<PrivateMessageDp> getPrivateMessageDpList(long dialogId);

	public int getLatestPrivateMessageIdByUserId(int id);

	public long getDialogIdByUserId(int userId1, int userId2);

	public boolean checkOperateValidation(int userId, long dialogId);

	public PrivateMessageDp getPrivateMessageDp(PrivateMessage privateMessage);

}
