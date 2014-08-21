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
	 * int type) { // ��дFollowed���͵� ��Щʱ��дQuestion���͵� List<Follow> followList =
	 * followService .getFollowListByFollowedUserId(userId); for (int i = 0; i <
	 * followList.size(); i++) { Follow follow = followList.get(i); Message
	 * message = new Message(follow.getFollowerId(), false, type, mainSourceId,
	 * relatedSourceId); messageService.addMessage(message); } }
	 */

	/*
	 * �û���ע���˲�������Ϣ
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
	 * �û���ע�������������Ϣ
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
	 * xxx����/��ע�� ����XXX
	 */
	public void addMessageByRecerver(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		Message message = new Message(receiverId, type, producerId,
				producerName, relatedSourceId, relatedSourceContent);
		messageService.addMessage(message);

	}

}
