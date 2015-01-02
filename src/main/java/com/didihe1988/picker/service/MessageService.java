package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.Dynamic;
import com.didihe1988.picker.model.display.Footprint;
import com.didihe1988.picker.model.display.MessageDp;

public interface MessageService {
	public int addMessage(Message message);

	public int updateMessage(Message message);

	public int deleteMessage(Message message);

	public List<Message> getMessageByReceiverId(int receiverId);

	public List<Message> getMessageByReceiverIdAndType(int receiverId, int type);

	public List<MessageDp> getMessageDpByReceiverIdAndType(int receiverId,
			int type);

	/**
	 * 
	 * @description �û��㼣userId:producerId ��̬��������� userId:receiverId
	 */
	public List<Message> getMessageByUserIdAndFilter(int userId,
			Message.Filter filter);

	public List<Message> getLimitedMessageByUserIdAndFilter(int userId,
			Message.Filter filter,int limit);

	public List<MessageDp> getMessageDpByUserIdAndFilter(int userId,
			Message.Filter filter);

	public List<Footprint> getFootprintByUserIdAndFilter(int userId,
			Message.Filter filter);

	public List<Dynamic> getDynamicByUserId(int userId);
	
	public List<Dynamic> getLimitedDynamicByUserId(int userId,int limit);

	public boolean isMessageExists(Message message);

	public boolean isUncheckedMessageExists(int receiverId);

	public Message getMessageById(int id);

	public MessageDp getMessageDpFromMessage(Message message);

	public Footprint getFootprintFromMessage(Message message);

	public int setMessageChecked(int id);

	/*
	 * �û���ע���˲�������Ϣ
	 */

	public void addMessageByFollowedUser(boolean isFeedRelated, int type,
			User producer, int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId);

	// public void addMessageByFollowedUser(FollowedMessage foMessage);

	/*
	 * �û���ע�������������Ϣ
	 */
	
	public void addMessageByFollowedQuestion(int type, User producer,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId);

	// public void addMessageByFollowedQuestion(FollowedMessage foMessage);

	/*
	 * xxx����/��ע�� ����XXX
	 */
	/*
	 * public void addMessageByRecerver(int receiverId, int type, int
	 * producerId, String producerName, int relatedSourceId, String
	 * relatedSourceContent, String extraContent, int parentId);
	 */
	public void addMessageByRecerver(int receiverId, boolean isFeedRelated,
			int type, User producer, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId);
}
