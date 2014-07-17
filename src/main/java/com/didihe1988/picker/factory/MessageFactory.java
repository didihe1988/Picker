package com.didihe1988.picker.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;

public class MessageFactory {
	@Autowired 
	private FollowService followService;
	
	@Autowired
	private MessageService messageService;
	
	public void addMessage(int userId,int sourseId,int type)
	{
		List<Follow> followList=followService.getFollowByFollowedUserId(userId);
		for(int i=0;i<followList.size();i++)
		{
			Follow  follow=followList.get(i);
			Message message=new Message(follow.getFollowerId(), false,type,sourseId);
			messageService.addMessage(message);
		}
	}
	
	
}
