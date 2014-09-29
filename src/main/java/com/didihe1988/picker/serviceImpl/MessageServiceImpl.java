package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.MessageDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	@Autowired
	private FollowDao followDao;

	/*
	 * 用户关注的人产生的消息
	 */
	@Override
	public void addMessageByFollowedUser(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		final List<Follow> followList = followDao
				.queryFollowListByFollowedUserId(producerId);
		for (Follow follow : followList) {
			Message message = new Message(follow.getFollowerId(), type,
					producerId, producerName, relatedSourceId,
					relatedSourceContent);
			messageDao.addMessage(message);
		}
	}

	/*
	 * 用户关注的问题产生的消息
	 */
	@Override
	public void addMessageByFollowedQuestion(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		final List<Follow> followList = followDao
				.queryFollowListByQuestionId(producerId);
		for (Follow follow : followList) {
			Message message = new Message(follow.getFollowerId(), type,
					producerId, producerName, relatedSourceId,
					relatedSourceContent);
			messageDao.addMessage(message);
		}
	}

	/*
	 * xxx赞了/关注了 您的XXX
	 */
	@Override
	public void addMessageByRecerver(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		Message message = new Message(receiverId, type, producerId,
				producerName, relatedSourceId, relatedSourceContent);
		messageDao.addMessage(message);

	}

	@Override
	public int addMessage(Message message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return Status.NULLPOINTER;
		}
		int status = messageDao.addMessage(message);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateMessage(Message message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return Status.NULLPOINTER;
		}
		int status = messageDao.updateMessage(message);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteMessage(Message message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return Status.NULLPOINTER;
		}
		int status = messageDao.deleteMessage(message);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Message> getMessageByReceiverId(int receiverId) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageByReceiverId(receiverId);
	}

	@Override
	public boolean isMessageExists(Message message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return false;
		}
		return messageDao.isMessageExists(message);
	}

	@Override
	public boolean isUncheckedMessageExists(int receiverId) {
		// TODO Auto-generated method stub
		return messageDao.isUncheckedMessageExists(receiverId);
	}

	@Override
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageById(id);
	}

	@Override
	public int setMessageChecked(int id) {
		// TODO Auto-generated method stub
		return messageDao.setMessageChecked(id);
	}

	@Override
	public List<Message> getMessageByReceiverIdAndType(int receiverId, int type) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageByReceiverIdAndType(receiverId, type);
	}

	@Override
	public List<Message> getMessageByReceiverIdAndFilter(int receiverId,
			Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
