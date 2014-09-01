package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleDp;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.UserDp;

public interface CircleMemberService {
	public int addCircleMember(CircleMember circleMember);

	public int updateCircleMember(CircleMember circleMember);

	public int deleteCircleMember(int userId, int circleId);

	public int deleteCircleMemberByEstablisher(int establisherId,
			CircleMember circleMember);

	public CircleMember getCircleMemberById(int id);

	public boolean isCircleMemberExistsByCircleIdMemberId(
			CircleMember circleMember);

	public boolean isCircleMemberExistsById(int id);

	public List<CircleMember> getCircleMemberListByCircleId(int id);

	public List<CircleMember> getCircleMemberListByMemberId(int id);

	public List<Circle> getCircleListByMemberId(int id);

	public List<CircleDp> getCircleDpListByMemberId(int id);

}
