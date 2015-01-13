package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.MessageDao;
import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.Dynamic;
import com.didihe1988.picker.model.display.Footprint;
import com.didihe1988.picker.model.display.MessageDp;
import com.didihe1988.picker.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	@Autowired
	private FollowDao followDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FeedDao feedDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private RelatedImageDao relatedImageDao;

	@Autowired
	private CircleDao circleDao;
	

	// 用户关注的人产生的消息
	@Override
	public void addMessageByFollowedUser(boolean isFeedRelated, int type,
			User producer, int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		// TODO Auto-generated method stub
		final List<Follow> followList = followDao
				.queryFollowListByFollowedUserId(producer.getId());
		for (Follow follow : followList) {
			addMessageByRecerver(follow.getFollowerId(), isFeedRelated, type,
					producer, relatedSourceId, relatedSourceContent,
					extraContent, parentId);
		}
	}

	// 用户关注的问题产生的消息
	@Override
	public void addMessageByFollowedQuestion(int type, User producer,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		// TODO Auto-generated method stub
		final List<Follow> followList = followDao
				.queryFollowListByQuestionId(producer.getId());
		for (Follow follow : followList) {
			addMessageByRecerver(follow.getFollowerId(), false, type, producer,
					relatedSourceId, relatedSourceContent, extraContent,
					parentId);
		}
	}

	// xxx赞了/关注了 您的XXX
	@Override
	public void addMessageByRecerver(int receiverId, boolean isFeedRelated,
			int type, User producer, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId) {
		// TODO Auto-generated method stub
		addMessageByRecerver(receiverId, isFeedRelated, type, producer.getId(),
				producer.getUsername(), relatedSourceId, relatedSourceContent,
				extraContent, parentId);
	}

	private void addMessageByRecerver(int receiverId, boolean isFeedRelated,
			int type, int producerId, String producerName, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId) {
		Message message = new Message(receiverId, isFeedRelated, type,
				producerId, producerName, relatedSourceId,
				relatedSourceContent, extraContent, parentId);
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
	/*
	@Override
	public List<Message> getMessagesByUserIdAndFilter(int userId,
			MessageFilter filter) {
		// TODO Auto-generated method stub
		return messageDao.queryMessagesByUserIdAndFilterType(userId, filter);
	}

	@Override
	public List<Message> getLimitedMessagesByUserIdAndFilter(int userId,
			MessageFilter filter, int limit) {
		// TODO Auto-generated method stub
		return messageDao.queryLimitedMessagesByUserIdAndFilterType(userId,
				filter, limit);
	}*/
	
	@Override
	public List<Message> getMessagesByUserIdAndFilter(int userId,
			Filter filter) {
		// TODO Auto-generated method stub
		return messageDao.queryMessagesByUserIdAndFilterType(userId, filter);
	}

	@Override
	public List<Message> getLimitedMessagesByUserIdAndFilter(int userId,
			Filter filter, int limit) {
		// TODO Auto-generated method stub
		return messageDao.queryLimitedMessagesByUserIdAndFilterType(userId,
				filter, limit);
	}
	

	@Override
	public MessageDp getMessageDpFromMessage(Message message) {
		// TODO Auto-generated method stub
		String avatarUrl = userDao.queryModelById(message.getProducerId())
				.getAvatarUrl();
		return new MessageDp(message, avatarUrl);
	}

	@Override
	public Footprint getFootprintFromMessage(Message message) {
		// TODO Auto-generated method stub
		String avatarUrl = userDao.queryModelById(message.getProducerId())
				.getAvatarUrl();
		int type = message.getType();
		String parentName = "";
		if (message.isFeedRelated()) {
			parentName = bookDao.queryModelById(message.getParentId())
					.getBookName();
		}
		return new Footprint(message, avatarUrl, parentName);
	}

	@Override
	public List<MessageDp> getMessageDpByReceiverIdAndType(int receiverId,
			int type) {
		// TODO Auto-generated method stub
		return toMessageDpList(getMessageByReceiverIdAndType(receiverId, type));
	}
	/*
	@Override
	public List<MessageDp> getMessageDpsByUserIdAndFilter(int userId,
			MessageFilter filter) {
		// TODO Auto-generated method stub
		return toMessageDpList(getMessagesByUserIdAndFilter(userId, filter));
	}

	@Override
	public List<MessageDp> getLimitedMessageDpsByUserIdAndFilter(int userId,
			MessageFilter filter, int limit) {
		// TODO Auto-generated method stub
		return toMessageDpList(getLimitedMessagesByUserIdAndFilter(userId,
				filter, limit));
	}

	@Override
	public List<Footprint> getFootprintsByUserId(int userId) {
		// TODO Auto-generated method stub
		return toFootprintList(getMessagesByUserIdAndFilter(userId,
				MessageFilter.FOOTPRINT));
	}

	@Override
	public List<Footprint> getLimitedFootprintsByUserId(int userId, int limit) {
		// TODO Auto-generated method stub
		return toFootprintList(getLimitedMessagesByUserIdAndFilter(userId,
				MessageFilter.FOOTPRINT, limit));
	}

	@Override
	public List<Dynamic> getDynamicsByUserId(int userId) {
		// TODO Auto-generated method stub
		return toDynamicList(getMessagesByUserIdAndFilter(userId,
				MessageFilter.DYNAMIC));
	}

	@Override
	public List<Dynamic> getLimitedDynamicsByUserId(int userId, int limit) {
		// TODO Auto-generated method stub
		return toDynamicList(getLimitedMessagesByUserIdAndFilter(userId,
				MessageFilter.DYNAMIC, limit));
	}*/
	
	@Override
	public List<MessageDp> getMessageDpsByUserIdAndFilter(int userId,
			Filter filter) {
		// TODO Auto-generated method stub
		return toMessageDpList(getMessagesByUserIdAndFilter(userId, filter));
	}

	@Override
	public List<MessageDp> getLimitedMessageDpsByUserIdAndFilter(int userId,
			Filter filter, int limit) {
		// TODO Auto-generated method stub
		return toMessageDpList(getLimitedMessagesByUserIdAndFilter(userId,
				filter, limit));
	}

	@Override
	public List<Footprint> getFootprintsByUserId(int userId) {
		// TODO Auto-generated method stub
		return toFootprintList(getMessagesByUserIdAndFilter(userId,
				Filter.MESSAGE_FOOTPRINT));
	}

	@Override
	public List<Footprint> getLimitedFootprintsByUserId(int userId, int limit) {
		// TODO Auto-generated method stub
		return toFootprintList(getLimitedMessagesByUserIdAndFilter(userId,
				Filter.MESSAGE_FOOTPRINT, limit));
	}

	@Override
	public List<Dynamic> getDynamicsByUserId(int userId) {
		// TODO Auto-generated method stub
		return toDynamicList(getMessagesByUserIdAndFilter(userId,
				Filter.MESSAGE_DYNAMIC));
	}

	@Override
	public List<Dynamic> getLimitedDynamicsByUserId(int userId, int limit) {
		// TODO Auto-generated method stub
		return toDynamicList(getLimitedMessagesByUserIdAndFilter(userId,
				Filter.MESSAGE_DYNAMIC, limit));
	}

	/*
	 * ---turn to SubMessgaeType starts---
	 */
	/*
	private Dynamic getDynamicFromMessage(Message message) {
		String avatarUrl = userDao.queryModelById(message.getProducerId())
				.getAvatarUrl();
		int type = message.getType();
		String parentName = "", imageUrl = "";

		if (message.isFeedRelated()) {
			parentName = bookDao.queryModelById(message.getParentId())
					.getBookName();
			imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					message.getRelatedSourceId(), RelatedImage.FEED_IMAGE);
		} else if (type == DynamicFilter.MESSAGE_FOLLOWED_FAVORITE_ANSWER) {
			imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					message.getRelatedSourceId(), RelatedImage.ANSWER_IMAGE);
		}
		return new Dynamic(message, avatarUrl, parentName, imageUrl);
	}*/
	
	private Dynamic getDynamicFromMessage(Message message) {
		String avatarUrl = userDao.queryModelById(message.getProducerId())
				.getAvatarUrl();
		int type = message.getType();
		String parentName = "", imageUrl = "";

		if (message.isFeedRelated()) {
			parentName = bookDao.queryModelById(message.getParentId())
					.getBookName();
			imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					message.getRelatedSourceId(), RelatedImage.FEED_IMAGE);
		} else if (type == Message.MESSAGE_FOLLOWED_FAVORITE_ANSWER) {
			imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					message.getRelatedSourceId(), RelatedImage.ANSWER_IMAGE);
		}
		return new Dynamic(message, avatarUrl, parentName, imageUrl);
	}
	
	private List<Dynamic> toDynamicList(List<Message> messages) {
		List<Dynamic> list = new ArrayList<Dynamic>();
		for (Message message : messages) {
			Dynamic dynamic = getDynamicFromMessage(message);
			list.add(dynamic);
		}
		return list;
	}

	private List<MessageDp> toMessageDpList(List<Message> messages) {
		List<MessageDp> list = new ArrayList<MessageDp>();
		for (Message message : messages) {
			MessageDp messageDp = getMessageDpFromMessage(message);
			list.add(messageDp);
		}
		return list;
	}

	private List<Footprint> toFootprintList(List<Message> messages) {
		List<Footprint> list = new ArrayList<Footprint>();
		for (Message message : messages) {
			Footprint fullMessage = getFootprintFromMessage(message);
			list.add(fullMessage);
		}
		return list;
	}
	/*
	 * ---turn to SubMessgaeType starts---
	 */
}
