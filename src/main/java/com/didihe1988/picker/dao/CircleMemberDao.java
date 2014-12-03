package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.CircleMember;

public interface CircleMemberDao extends BaseDao<CircleMember>,
		OperateValidation {
	public CircleMember queryModelById(int id);

	// public CircleMember queryCircleMemberByKey(int userId, int circleId);

	public int deleteCircleMember(int userId, int circleId);

	public boolean isCircleMemberExists(CircleMember circleMember);

	public boolean isUserInCircle(int userId, int circleId);

	public List<CircleMember> queryCircleMemberListByCircleId(int id);

	public List<CircleMember> queryCircleMemberListByMemberId(int id);

}
