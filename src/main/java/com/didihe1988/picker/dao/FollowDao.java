package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.Follow;

public interface FollowDao extends OperateValidation {
	public Follow queryFollowById(int id);

	public int addFollow(Follow follow);

	public int deleteFollow(int sourceType, int followerId, int sourceId);

	public int updateFollow(Follow follow);

	public boolean isFollowExistsById(int id);

	public boolean isFollowExistsByKey(int sourceType, int followerId,
			int sourceId);

	public List<Follow> queryFollowListByFollowerId(int followerId);

	public List<Follow> queryFollowListByFollowerIdByType(int followerId,
			int type);

	public List<Follow> queryFollowListByFollowedUserId(int followedUserId);

	public List<Follow> queryFollowListByQuestionId(int questionId);

	public List<Follow> queryAllForTest();
}
