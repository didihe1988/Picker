package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.CircleMember;

public interface CircleMemberService {
	public int addCircleMember(CircleMember circleMember);

	public int updateCircleMember(CircleMember circleMember);

	public int deleteCircleMember(CircleMember circleMember);

	public int deleteCircleMemberByEstablisher(int establisherId,
			CircleMember circleMember);

	public CircleMember getCircleMemberById(int id);

	public boolean isCircleMemberExistsByCircleIdMemberId(
			CircleMember circleMember);

	public boolean isCircleMemberExistsById(int id);

	public List<CircleMember> getCircleMemberListByCircleId(int id);

	public List<CircleMember> getCircleMemberListByMemberId(int id);

}
