package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Message;

public interface MessageService {
	public int addMessage(Message message);

	public int updateMessage(Message message);

	public int deleteMessage(Message message);

	public List<Message> getMessageByReceiverId(int receiverId);

	public boolean isMessageExists(Message message);

	public Message getMessageById(int id);

	public int setMessageChecked(int id);
}