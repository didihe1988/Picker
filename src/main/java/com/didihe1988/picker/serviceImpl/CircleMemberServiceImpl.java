package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.dao.CircleMemberDao;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.service.CircleMemberService;

@Service
@Transactional
public class CircleMemberServiceImpl implements CircleMemberService {
	@Autowired
	private CircleMemberDao circleMemberDao;

	@Autowired
	private CircleDao circleDao;

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
		 * Circle��memberNum++
		 */
		circleDao.incrementNum("memberNum", circleMember.getCircleId());
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
	 * @description �����Լ��˳�Ȧ��
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
		 * Circle��memberNum--
		 */
		circleDao.decrementNum(Circle.CIRCLE_MEMBER_NUM,
				circleMember.getCircleId());
		return Status.SUCCESS;
	}

	/**
	 * @description Ȧ�ӵĴ����߿���ɾ��
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
		 * Circle��memberNum--
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

}