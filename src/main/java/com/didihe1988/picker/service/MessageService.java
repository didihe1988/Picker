package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Message;

public interface MessageService {
	public int addMessage(Message message);

	public int updateMessage(Message message);

	public int deleteMessage(Message message);

	public List<Message> getMessageByReceiverId(int receiverId);

	public List<Message> getMessageByReceiverIdAndType(int receiverId, int type);
	
	/**
	 * 
	 * @description 用户足迹objId:producerId  动态、与我相关 objId:receiverId
	 */
	public List<Message> getMessageByReceiverIdAndFilter(int objId,Message.Filter filter);

	public boolean isMessageExists(Message message);

	public boolean isUncheckedMessageExists(int receiverId);

	public Message getMessageById(int id);

	public int setMessageChecked(int id);

	/*
	 * 用户关注的人产生的消息
	 */
	public void addMessageByFollowedUser(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent);

	/*
	 * 用户关注的问题产生的消息
	 */
	public void addMessageByFollowedQuestion(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent);

	/*
	 * xxx赞了/关注了 您的XXX
	 */
	public void addMessageByRecerver(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent);

}
