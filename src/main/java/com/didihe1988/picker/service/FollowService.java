package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.dp.UserDp;

public interface FollowService {
	public Follow getFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(int sourceType, int followerId, int sourceId);

	public int updateFollow(Follow follow);

	public boolean isFollowExistsById(int id);

	public boolean isFollowExistsByKey(int sourceType, int followerId,
			int sourceId);

	/**
	 * @description �û����Բ鿴�Լ���ע����������
	 */
	public List<Follow> getFollowListByFollowerId(int followerId);

	/**
	 * @description �û����Բ鿴�Լ���ע����������
	 */
	public List<Follow> getFollowListByFollowerIdByQuestion(int followerId);

	/**
	 * @description �û����Բ鿴�Լ���ע�������û�
	 */
	public List<Follow> getFollowListByFollowerIdByUser(int followerId);

	/**
	 * @description ���û�����Щ�˹�ע
	 */
	public List<Follow> getFollowListByFollowedUserId(int followedUserId);

	/**
	 * @description �����ⱻ��Щ�˹�ע
	 */
	public List<Follow> getFollowListByQuestionId(int questionId);

	public List<Follow> getAllFollowForTest();

}
