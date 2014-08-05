package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.model.Follow;

public interface FollowDao extends DeleteValidation {
	public Follow queryFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(Follow follow);

	public int updateFollow(Follow follow);

	public boolean isFollowExists(Follow follow);

	public List<Follow> queryFollowListByFollowerId(int followerId);

	public List<Follow> queryFollowListByFollowedUserId(int followedUserId);
	
	public List<Follow> queryFollowListByQuestionId(int questionId);

	public boolean isFollowExists(int id);

	public List<Follow> queryAllForTest();
}
