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
import com.didihe1988.picker.model.dp.Dynamic;
import com.didihe1988.picker.model.dp.Footprint;
import com.didihe1988.picker.model.dp.MessageDp;
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

	/*
	 * 用户关注的人产生的消息
	 */
	@Override
	public void addMessageByFollowedUser(int type, User producer,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		// TODO Auto-generated method stub
		final List<Follow> followList = followDao
				.queryFollowListByFollowedUserId(producer.getId());
		for (Follow follow : followList) {
			addMessageByRecerver(follow.getFollowerId(), type, producer,
					relatedSourceId, relatedSourceContent, extraContent,
					parentId);
		}
	}

	/*
	 * 用户关注的问题产生的消息
	 */
	@Override
	public void addMessageByFollowedQuestion(int type, User producer,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		// TODO Auto-generated method stub
		final List<Follow> followList = followDao
				.queryFollowListByQuestionId(producer.getId());
		for (Follow follow : followList) {
			addMessageByRecerver(follow.getFollowerId(), type, producer,
					relatedSourceId, relatedSourceContent, extraContent,
					parentId);
		}
	}

	/*
	 * xxx赞了/关注了 您的XXX
	 */
	@Override
	public void addMessageByRecerver(int receiverId, int type, User producer,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		// TODO Auto-generated method stub
		addMessageByRecerver(receiverId, type, producer.getId(),
				producer.getUsername(), relatedSourceId, relatedSourceContent,
				extraContent, parentId);
	}

	private void addMessageByRecerver(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId) {
		Message message = new Message(receiverId, type, producerId,
				producerName, relatedSourceId, relatedSourceContent,
				extraContent, parentId);
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
	public List<Message> getMessageByUserIdAndFilter(int objId, Filter filter) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageByUserIdAndFilter(objId, filter);
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
		/*
		 * if ((type == Message.MESSAGE_USER_ADDQUESTION) || (type ==
		 * Message.MESSAGE_USER_ADDNOTE) || (type ==
		 * Message.MESSAGE_USER_FAVORITE_QUESTION) || (type ==
		 * Message.MESSAGE_USER_FAVORITE_NOTE))
		 */
		if (message.isFeedRelated()) {
			parentName = bookDao.queryModelById(message.getParentId())
					.getBookName();
		} else if ((type == Message.MESSAGE_USER_ADDANSWER)
				|| (type == Message.MESSAGE_USER_FAVORITE_ANSWER)) {
		}
		return new Footprint(message, avatarUrl, parentName);
	}

	private Dynamic getDynamicFromMessage(Message message) {
		String avatarUrl = userDao.queryModelById(message.getProducerId())
				.getAvatarUrl();
		int type = message.getType();
		String parentName = "", imageUrl = "";
		/*
		 * if ((type == Message.MESSAGE_FOLLOWED_FAVORITE_QEUSTION) || (type ==
		 * Message.MESSAGE_FOLLOWED_ANSWER_QUESTION) || (type ==
		 * Message.MESSAGE_FOLLOWED_ASKQUESTION) || (type ==
		 * Message.MESSAGE_FOLLOWED_FAVORITE_NOTE) || (type ==
		 * Message.MESSAGE_FOLLOWED_ADDNOTE))
		 */if (message.isFeedRelated()) {
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

	private List<Dynamic> getDynamicList(List<Message> messages) {
		List<Dynamic> list = new ArrayList<Dynamic>();
		for (Message message : messages) {
			Dynamic dynamic = getDynamicFromMessage(message);
			list.add(dynamic);
		}
		return list;
	}

	private List<MessageDp> getMessageDpList(List<Message> messages) {
		List<MessageDp> list = new ArrayList<MessageDp>();
		for (Message message : messages) {
			MessageDp messageDp = getMessageDpFromMessage(message);
			list.add(messageDp);
		}
		return list;
	}

	private List<Footprint> getFootprintList(List<Message> messages) {
		List<Footprint> list = new ArrayList<Footprint>();
		for (Message message : messages) {
			Footprint fullMessage = getFootprintFromMessage(message);
			list.add(fullMessage);
		}
		return list;
	}

	@Override
	public List<MessageDp> getMessageDpByReceiverIdAndType(int receiverId,
			int type) {
		// TODO Auto-generated method stub
		return getMessageDpList(getMessageByReceiverIdAndType(receiverId, type));
	}

	@Override
	public List<MessageDp> getMessageDpByUserIdAndFilter(int objId,
			Filter filter) {
		// TODO Auto-generated method stub
		return getMessageDpList(getMessageByUserIdAndFilter(objId, filter));
	}

	@Override
	public List<Footprint> getFootprintByUserIdAndFilter(int userId,
			Filter filter) {
		// TODO Auto-generated method stub
		return getFootprintList(getMessageByUserIdAndFilter(userId, filter));
	}

	@Override
	public List<Dynamic> getDynamicByUserId(int userId) {
		// TODO Auto-generated method stub
		return getDynamicList(getMessageByUserIdAndFilter(userId,
				Message.Filter.MESSAGE_DYNAMIC));
	}

}
