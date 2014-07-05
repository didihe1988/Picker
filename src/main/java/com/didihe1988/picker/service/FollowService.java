package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Follow;

public interface FollowService {
	public Follow getFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);
	
	public int setFollowchecked(int id);

	public List<Follow> getFollowByFollowerId(int followerId);

	public List<Follow> getUnckeckedFollowByFollowerId(int followerId);
}
