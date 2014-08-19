package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.model.CircleMember;

public interface CircleMemberDao extends DeleteValidation{
	public CircleMember queryCircleMemberById(int id);

	public int addCircleMember(CircleMember circleMember);

	public int deleteCircleMember(CircleMember circleMember);

	public int updateCircleMember(CircleMember circleMember);

	public boolean isCircleMemberExistsByCircleIdMemberId(
			CircleMember circleMember);

	public boolean isCircleMemberExistsById(int id);

	public List<CircleMember> queryCircleMemberListByCircleId(int id);

	public List<CircleMember> queryCircleMemberListByMemberId(int id);
	

}
