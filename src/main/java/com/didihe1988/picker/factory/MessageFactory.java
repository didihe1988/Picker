package com.didihe1988.picker.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.FollowService;
import com.didihe1988.picker.service.MessageService;

@Component
public class MessageFactory {
	@Autowired
	private FollowService followService;

	@Autowired
	private MessageService messageService;

	public void addMessage(int userId, int mainSourceId, int relatedSourceId,
			int type) {
		// ��дFollowed���͵� ��Щʱ��дQuestion���͵�
		List<Follow> followList = followService
				.getFollowByFollowedUserId(userId);
		for (int i = 0; i < followList.size(); i++) {
			Follow follow = followList.get(i);
			Message message = new Message(follow.getFollowerId(), false, type,
					mainSourceId, relatedSourceId);
			messageService.addMessage(message);
		}
	}

}
