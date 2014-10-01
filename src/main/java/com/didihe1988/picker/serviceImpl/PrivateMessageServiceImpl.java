package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.PrivateMessageDao;
import com.didihe1988.picker.model.PrivateMessage;
import com.didihe1988.picker.service.PrivateMessageService;

@Service
@Transactional
public class PrivateMessageServiceImpl implements PrivateMessageService {
	@Autowired
	private PrivateMessageDao privateMessageDao;

	@Override
	public PrivateMessage getPrivateMessageById(int id) {
		// TODO Auto-generated method stub
		return privateMessageDao.queryPrivateMessageById(id);
	}

	@Override
	public int addPrivateMessage(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		if (privateMessage == null) {
			return Status.NULLPOINTER;
		}
		int status = privateMessageDao.addPrivateMessage(privateMessage);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deletePrivateMessageById(int id) {
		// TODO Auto-generated method stub
		int status = privateMessageDao.deletePrivateMessageById(id);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updatePrivateMessage(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		if (privateMessage == null) {
			return Status.NULLPOINTER;
		}
		int status = privateMessageDao.updatePrivateMessage(privateMessage);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isPrivateMessageExistsById(int id) {
		// TODO Auto-generated method stub
		return privateMessageDao.isPrivateMessageExistsById(id);
	}

	@Override
	public List<PrivateMessage> getPrivateMessageByUserId(int userId) {
		// TODO Auto-generated method stub
		return privateMessageDao.queryPrivateMessageByUserId(userId);
	}

	@Override
	public int getLatestPrivateMessageIdByUserId(int id) {
		// TODO Auto-generated method stub
		return privateMessageDao.getLatestPrivateMessageIdByUserId(id);
	}

	@Override
	public long getDialogIdByUserId(int userId1, int userId2) {
		// TODO Auto-generated method stub
		return privateMessageDao.getDialogIdByUserId(userId1, userId2);
	}

}
