package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Follow;

public interface FollowDao {
	public Follow queryFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);

	public List<Follow> queryFollowByFollowerId(int followerId);
}
