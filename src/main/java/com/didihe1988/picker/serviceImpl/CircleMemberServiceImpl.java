package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.dao.CircleMemberDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleDp;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.service.CircleMemberService;

@Service
@Transactional
public class CircleMemberServiceImpl implements CircleMemberService {
	@Autowired
	private CircleMemberDao circleMemberDao;

	@Autowired
	private CircleDao circleDao;

	@Autowired
	private UserDao userDao;

	@Override
	public int addCircleMember(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return Status.NULLPOINTER;
		}
		int status = circleMemberDao.addCircleMember(circleMember);
		if (status == -1) {
			return Status.EXISTS;
		}
		/*
		 * Circle的memberNum++
		 */
		circleDao.incrementNum("memberNum", circleMember.getCircleId());

		/*
		 * User circleNum++
		 */
		userDao.incrementNum(Constant.CIRCLE_NUM, circleMember.getMemberId());
		return Status.SUCCESS;

	}

	@Override
	public int updateCircleMember(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return Status.NULLPOINTER;
		}
		int status = circleMemberDao.updateCircleMember(circleMember);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	/**
	 * @description 本人自己退出圈子
	 */
	@Override
	public int deleteCircleMember(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return Status.NULLPOINTER;
		}
		if (!circleMemberDao.checkDeleteValidation(circleMember.getMemberId(),
				circleMember.getId())) {
			return Status.INVALID;
		}

		circleMemberDao.deleteCircleMember(circleMember);
		/*
		 * Circle的memberNum--
		 */
		circleDao.decrementNum(Circle.CIRCLE_MEMBER_NUM,
				circleMember.getCircleId());
		return Status.SUCCESS;
	}

	/**
	 * @description 圈子的创建者可以删除
	 */
	@Override
	public int deleteCircleMemberByEstablisher(int establisherId,
			CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return Status.NULLPOINTER;
		}
		int circleId = circleMember.getCircleId();
		if (!circleDao.isEstablisherOfCircle(establisherId, circleId)) {
			return Status.INVALID;
		}
		circleMemberDao.deleteCircleMember(circleMember);
		/*
		 * Circle的memberNum--
		 */
		circleDao.decrementNum(Circle.CIRCLE_MEMBER_NUM,
				circleMember.getCircleId());
		return Status.SUCCESS;
	}

	@Override
	public CircleMember getCircleMemberById(int id) {
		// TODO Auto-generated method stub
		return circleMemberDao.queryCircleMemberById(id);
	}

	@Override
	public boolean isCircleMemberExistsByCircleIdMemberId(
			CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return false;
		}
		return circleMemberDao
				.isCircleMemberExistsByCircleIdMemberId(circleMember);
	}

	@Override
	public boolean isCircleMemberExistsById(int id) {
		// TODO Auto-generated method stub
		return circleMemberDao.isCircleMemberExistsById(id);
	}

	@Override
	public List<CircleMember> getCircleMemberListByCircleId(int id) {
		// TODO Auto-generated method stub
		return circleMemberDao.queryCircleMemberListByCircleId(id);
	}

	@Override
	public List<CircleMember> getCircleMemberListByMemberId(int id) {
		// TODO Auto-generated method stub
		return circleMemberDao.queryCircleMemberListByMemberId(id);
	}

	@Override
	public List<Circle> getCircleListByMemberId(int id) {
		// TODO Auto-generated method stub
		List<CircleMember> cirMembers = getCircleMemberListByMemberId(id);
		List<Circle> list = new ArrayList<Circle>();
		for (CircleMember circleMember : cirMembers) {
			Circle circle = circleDao.queryCircleById(circleMember
					.getCircleId());
			list.add(circle);
		}
		return list;
	}

	@Override
	public List<CircleDp> getCircleDpListByMemberId(int id) {
		// TODO Auto-generated method stub
		List<CircleMember> cirMembers = getCircleMemberListByMemberId(id);
		List<CircleDp> list = new ArrayList<CircleDp>();
		for (CircleMember circleMember : cirMembers) {
			Circle circle = circleDao.queryCircleById(circleMember
					.getCircleId());
			CircleDp circleDp = new CircleDp(circle, circleMember.getJoinTime());
			list.add(circleDp);
		}
		return list;
	}

}
