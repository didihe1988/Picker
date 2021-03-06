package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.DialogDao;
import com.didihe1988.picker.dao.PrivateMessageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.PrivateMessage;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.PrivateMessageDp;
import com.didihe1988.picker.model.display.PrivateMessageSum;
import com.didihe1988.picker.service.PrivateMessageService;
import com.didihe1988.picker.utils.DateUtils;

@Service
@Transactional
public class PrivateMessageServiceImpl implements PrivateMessageService {
	@Autowired
	private PrivateMessageDao privateMessageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DialogDao dialogDao;

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

	@Override
	public List<PrivateMessage> getPrivateMessageByDialogId(long dialogId) {
		// TODO Auto-generated method stub
		return privateMessageDao.queryPrivateMessageByDialogId(dialogId);
	}

	@Override
	public boolean checkOperateValidation(int userId, long dialogId) {
		// TODO Auto-generated method stub
		return privateMessageDao.checkOperateValidation(userId, dialogId);
	}

	@Override
	public PrivateMessageDp getPrivateMessageDp(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		User receiver = userDao.queryModelById(privateMessage.getReceiverId());
		User sender = userDao.queryModelById(privateMessage.getSenderId());
		return new PrivateMessageDp(privateMessage, sender.getUsername(),
				sender.getAvatarUrl(), receiver.getUsername(),
				receiver.getAvatarUrl(), DateUtils.toDate(privateMessage
						.getTime()));
	}

	@Override
	public List<PrivateMessageDp> getPrivateMessageDpList(long dialogId) {
		// TODO Auto-generated method stub
		return getPrivateMessageDpList(getPrivateMessageByDialogId(dialogId));
	}

	private List<PrivateMessageDp> getPrivateMessageDpList(
			List<PrivateMessage> privateMessages) {
		// TODO Auto-generated method stub
		List<PrivateMessageDp> list = new ArrayList<PrivateMessageDp>();
		for (PrivateMessage privateMessage : privateMessages) {
			PrivateMessageDp privateMessageDp = getPrivateMessageDp(privateMessage);
			list.add(privateMessageDp);
		}
		return list;
	}

	@Override
	public List<PrivateMessageSum> getPrivateMessageSumByUserId(int userId) {
		// TODO Auto-generated method stub
		return getPrivateMessageSumList(getPrivateMessageByUserId(userId));
	}

	private PrivateMessageSum getPrivateMessageSum(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		User receiver = userDao.queryModelById(privateMessage.getReceiverId());
		User sender = userDao.queryModelById(privateMessage.getSenderId());
		int count = dialogDao.queryDialogById(privateMessage.getDialogId())
				.getCount();
		return new PrivateMessageSum(privateMessage, sender.getUsername(),
				sender.getAvatarUrl(), receiver.getUsername(),
				receiver.getAvatarUrl(), DateUtils.toDate(privateMessage
						.getTime()), count);
	}

	private List<PrivateMessageSum> getPrivateMessageSumList(
			List<PrivateMessage> privateMessages) {
		// TODO Auto-generated method stub
		List<PrivateMessageSum> list = new ArrayList<PrivateMessageSum>();
		for (PrivateMessage privateMessage : privateMessages) {
			PrivateMessageSum privateMessageSum = getPrivateMessageSum(privateMessage);
			list.add(privateMessageSum);
		}
		return list;
	}

}
