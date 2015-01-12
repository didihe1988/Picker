package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.CircleWebDp;
import com.didihe1988.picker.service.CircleMemberService;
import com.didihe1988.picker.utils.DateUtils;

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
		int status = circleMemberDao.addModel(circleMember);
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
		int status = circleMemberDao.updateModel(circleMember);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	/**
	 * @description 本人自己退出圈子
	 */
	@Override
	public int deleteCircleMember(int userId, int circleId) {
		// TODO Auto-generated method stub
		/*
		 * check validation if
		 * (!circleMemberDao.checkOperateValidation(circleMember.getMemberId(),
		 * circleMember.getId())) { return Status.INVALID; }
		 */

		circleMemberDao.deleteCircleMember(userId, circleId);
		/*
		 * Circle的memberNum--
		 */
		circleDao.decrementNum(Circle.CIRCLE_MEMBER_NUM, circleId);
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
		// circleMemberDao.deleteCircleMember(circleMember);
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
		return circleMemberDao.queryModelById(id);
	}

	@Override
	public boolean isCircleMemberExists(CircleMember circleMember) {
		// TODO Auto-generated method stub
		if (circleMember == null) {
			return false;
		}
		return circleMemberDao.isCircleMemberExists(circleMember);
	}

	@Override
	public boolean isCircleMemberExistsById(int id) {
		// TODO Auto-generated method stub
		return circleMemberDao.isModelExistsById(id);
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
		final List<CircleMember> cirMembers = getCircleMemberListByMemberId(id);
		List<Circle> list = new ArrayList<Circle>();
		for (CircleMember circleMember : cirMembers) {
			Circle circle = circleDao.queryModelById(circleMember
					.getCircleId());
			list.add(circle);
		}
		return list;
	}

	private List<CircleWebDp> getCircleWebDpList(
			List<CircleMember> circleMembers) {
		List<CircleWebDp> list = new ArrayList<CircleWebDp>();
		for (CircleMember circleMember : circleMembers) {
			Circle circle = circleDao.queryModelById(circleMember
					.getCircleId());
			CircleWebDp circleWebDp = new CircleWebDp(circle,
					DateUtils.toDate(circleMember.getJoinTime()));
			list.add(circleWebDp);
		}
		return list;
	}

	@Override
	public List<CircleWebDp> getCircleWebDpListByMemberId(int id) {
		// TODO Auto-generated method stub
		return getCircleWebDpList(getCircleMemberListByMemberId(id));
	}

	@Override
	public List<CircleWebDp> getCircleWebDpListByCircleId(int id) {
		// TODO Auto-generated method stub
		return getCircleWebDpList(getCircleMemberListByCircleId(id));
	}

}
