package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.validation.DeleteValidation;

public interface FollowDao extends DeleteValidation {
	public Follow queryFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);

	public List<Follow> queryFollowByFollowerId(int followerId);

	public List<Follow> queryFollowByFollowedUserId(int followedUserId);

	// public List<Follow> queryUnckeckedFollowByFollowerId(int followerId);

	// public int updateFollowSetIsCheckedReverse(int id);

	public boolean isFollowExists(int id);

	public List<Follow> queryAllForTest();
}
