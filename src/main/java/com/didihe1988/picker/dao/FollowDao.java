package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.Follow;

public interface FollowDao extends BaseDao<Follow>, OperateValidation {
	public Follow queryModelById(int id);

	public int deleteFollow(int sourceType, int followerId, int sourceId);

	public boolean isFollowExistsByKey(int sourceType, int followerId,
			int sourceId);

	public List<Follow> queryFollowListByFollowerId(int followerId);

	public List<Follow> queryFollowListByFollowerIdByType(int followerId,
			int type);

	public List<Follow> queryFollowListByFollowedUserId(int followedUserId);

	public List<Follow> queryFollowListByQuestionId(int questionId);

	public List<Follow> queryAllForTest();
}
