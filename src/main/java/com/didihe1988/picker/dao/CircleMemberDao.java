package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.model.CircleMember;

public interface CircleMemberDao extends OperateValidation {
	public CircleMember queryCircleMemberById(int id);

	// public CircleMember queryCircleMemberByKey(int userId, int circleId);

	public int addCircleMember(CircleMember circleMember);

	public int deleteCircleMember(int userId, int circleId);

	public int updateCircleMember(CircleMember circleMember);

	public boolean isCircleMemberExistsByCircleIdMemberId(
			CircleMember circleMember);

	public boolean isCircleMemberExistsById(int id);

	public List<CircleMember> queryCircleMemberListByCircleId(int id);

	public List<CircleMember> queryCircleMemberListByMemberId(int id);

}
