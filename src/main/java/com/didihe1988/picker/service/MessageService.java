package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.Dynamic;
import com.didihe1988.picker.model.display.Footprint;
import com.didihe1988.picker.model.display.MessageDp;
import com.didihe1988.picker.model.message.MessageFilter;

public interface MessageService {

	public int addMessage(Message message);

	public int updateMessage(Message message);

	public int deleteMessage(Message message);

	public boolean isMessageExists(Message message);

	public boolean isUncheckedMessageExists(int receiverId);

	public Message getMessageById(int id);

	public MessageDp getMessageDpFromMessage(Message message);

	public Footprint getFootprintFromMessage(Message message);

	public int setMessageChecked(int id);

	// 用户关注的人产生的消息
	public void addMessageByFollowedUser(boolean isFeedRelated, int filterType,
			int type, User producer, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId);

	// 用户关注的问题产生的消息
	public void addMessageByFollowedQuestion(int filterType, int type,
			User producer, int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId);

	// xxx赞了/关注了 您的XXX
	public void addMessageByRecerver(int receiverId, boolean isFeedRelated,
			int filterType, int type, User producer, int relatedSourceId,
			String relatedSourceContent, String extraContent, int parentId);

	/*
	 * ---getMessageList starts---
	 */

	public List<Message> getMessageByReceiverId(int receiverId);

	public List<Message> getMessageByReceiverIdAndType(int receiverId, int type);

	public List<MessageDp> getMessageDpByReceiverIdAndType(int receiverId,
			int type);

	/**
	 * @param userId 对用户足迹来说:producerId 动态、与我相关:receiverId
	 */
	public List<Message> getMessagesByUserIdAndFilter(int userId,
			MessageFilter filter);

	public List<Message> getLimitedMessagesByUserIdAndFilter(int userId,
			MessageFilter filter, int limit);

	public List<MessageDp> getMessageDpsByUserIdAndFilter(int userId,
			MessageFilter filter);

	public List<MessageDp> getLimitedMessageDpsByUserIdAndFilter(int userId,
			MessageFilter filter, int limit);

	public List<Footprint> getFootprintsByUserId(int userId);

	public List<Footprint> getLimitedFootprintsByUserId(int userId, int limit);

	public List<Dynamic> getDynamicsByUserId(int userId);

	public List<Dynamic> getLimitedDynamicsByUserId(int userId, int limit);

	/*
	 * ---getMessageList ends---
	 */
}
