package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Follow;

public interface FollowService {
	public Follow getFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);


	public List<Follow> getFollowListByFollowerId(int followerId);

	public List<Follow> getFollowListByFollowedUserId(int followedUserId);
	
	public List<Follow> queryFollowListByQuestionId(int questionId);
	
	public List<Follow> getAllFollowForTest();

	
}
