package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.display.MessageDp;
import com.didihe1988.picker.model.message.MessageFilter;

public interface MessageDao {
	public Message queryMessageById(int id);

	public int addMessage(Message message);

	public int deleteMessage(Message message);

	public int updateMessage(Message message);

	public boolean isMessageExists(Message message);

	public boolean isUncheckedMessageExists(int receiverId);

	public List<Message> queryMessageByReceiverId(int receiverId);

	public int setMessageChecked(int id);

	public List<Message> queryMessageByReceiverIdAndType(int receiverId,
			int type);

	public List<Message> queryMessagesByUserIdAndFilterType(int userId,
			MessageFilter filter);

	public List<Message> queryLimitedMessagesByUserIdAndFilterType(int userId,
			MessageFilter filter, int limit);

}
