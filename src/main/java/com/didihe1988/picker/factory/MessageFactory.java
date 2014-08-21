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

	/*
	 * public void addMessage(int userId, int mainSourceId, int relatedSourceId,
	 * int type) { // 先写Followed类型的 晚些时候写Question类型的 List<Follow> followList =
	 * followService .getFollowListByFollowedUserId(userId); for (int i = 0; i <
	 * followList.size(); i++) { Follow follow = followList.get(i); Message
	 * message = new Message(follow.getFollowerId(), false, type, mainSourceId,
	 * relatedSourceId); messageService.addMessage(message); } }
	 */

	/*
	 * 用户关注的人产生的消息
	 */
	public void addMessageByFollowedUser(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		List<Follow> followList = followService
				.getFollowListByFollowedUserId(producerId);
		for (Follow follow : followList) {
			Message message = new Message(follow.getFollowerId(), type,
					producerId, producerName, relatedSourceId,
					relatedSourceContent);
			messageService.addMessage(message);
		}
	}

	/*
	 * 用户关注的问题产生的消息
	 */
	public void addMessageByFollowedQuestion(int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		List<Follow> followList = followService
				.getFollowListByQuestionId(producerId);
		for (Follow follow : followList) {
			Message message = new Message(follow.getFollowerId(), type,
					producerId, producerName, relatedSourceId,
					relatedSourceContent);
			messageService.addMessage(message);
		}
	}

	/*
	 * xxx赞了/关注了 您的XXX
	 */
	public void addMessageByRecerver(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		Message message = new Message(receiverId, type, producerId,
				producerName, relatedSourceId, relatedSourceContent);
		messageService.addMessage(message);

	}

}
