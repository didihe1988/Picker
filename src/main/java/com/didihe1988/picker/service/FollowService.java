package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.UserDp;

public interface FollowService {
	public Follow getFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);

	/**
	 * @description 用户可以查看自己关注的所有内容
	 */
	public List<Follow> getFollowListByFollowerId(int followerId);

	/**
	 * @description 用户可以查看自己关注的所有问题
	 */
	public List<Follow> getFollowListByFollowerIdByQuestion(int followerId);

	/**
	 * @description 用户可以查看自己关注的所有用户
	 */
	public List<Follow> getFollowListByFollowerIdByUser(int followerId);

	/**
	 * @description 该用户被那些人关注
	 */
	public List<Follow> getFollowListByFollowedUserId(int followedUserId);

	/**
	 * @description 该问题被那些人关注
	 */
	public List<Follow> getFollowListByQuestionId(int questionId);

	public List<Follow> getAllFollowForTest();

}
