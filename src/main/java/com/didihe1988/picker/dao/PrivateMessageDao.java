package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.model.PrivateMessage;

public interface PrivateMessageDao extends OperateValidation {
	public PrivateMessage queryPrivateMessageById(int id);

	public int addPrivateMessage(PrivateMessage privateMessage);

	public int deletePMById(int id);

	public int updatePrivateMessage(PrivateMessage privateMessage);

	public boolean isPrivateMessageExistsById(int id);

	public List<PrivateMessage> queryPrivateMessageByUserId(int userId1,
			int userId2);

	public int getLatestPrivateMessageIdByUserId(int id);

}
