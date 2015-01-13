package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.dao.CircleMemberDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.model.display.CircleDp;
import com.didihe1988.picker.service.CircleService;

@Service
@Transactional
public class CircleServiceImpl implements CircleService {
	@Autowired
	private CircleDao circleDao;

	@Autowired
	private CircleMemberDao circleMemberDao;

	@Autowired
	private UserDao userDao;


	@Override
	public int addCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return Status.NULLPOINTER;
		}
		int status = circleDao.addModel(circle);
		if (status == -1) {
			return Status.EXISTS;
		}

		/*
		 * CircleMember中有创建者
		 */
		int circleId = getLatestCircleIdByEstablisherId(circle
				.getEstablisherId());
		CircleMember circleMember = new CircleMember(circleId,
				circle.getEstablisherId());
		circleMemberDao.addModel(circleMember);
		return Status.SUCCESS;
	}

	@Override
	public int deleteCirvle(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return Status.NULLPOINTER;
		}
		// 删除之前确认是否可以删除
		if (!circleDao.checkOperateValidation(circle.getEstablisherId(),
				circle.getId())) {
			return Status.INVALID;
		}
		circleDao.deleteCircle(circle);
		return Status.SUCCESS;
	}

	@Override
	public int updateCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return Status.NULLPOINTER;
		}
		int status = circleDao.updateModel(circle);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isCircleExistsById(int id) {
		// TODO Auto-generated method stub
		return circleDao.isModelExistsById(id);
	}

	@Override
	public boolean isCircleExistsByName(String name) {
		// TODO Auto-generated method stub
		return circleDao.isCircleExistsByName(name);
	}

	@Override
	public Circle getCircleById(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryModelById(id);
	}

	@Override
	public CircleDp getCircleDpById(int id, int curUserId) {
		// TODO Auto-generated method stub
		Circle circle = circleDao.queryModelById(id);
		String establisherName = userDao.queryModelById(
				circle.getEstablisherId()).getUsername();
		return new CircleDp(circle, circleMemberDao.isUserInCircle(curUserId,
				id), establisherName);
	}

	@Override
	public List<Circle> getCircleListByEstablisherId(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryCircleListByEstablisherId(id);
	}

	@Override
	public boolean checkDeleteValidation(int establisherId, int circleId) {
		// TODO Auto-generated method stub
		return circleDao.checkOperateValidation(establisherId, circleId);
	}

	@Override
	public int getLatestCircleIdByEstablisherId(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryLatestCircleIdByEstablisherId(id);
	}

	@Override
	public List<Circle> search(String name) {
		// TODO Auto-generated method stub
		return circleDao.search(name);
	}

	@Override
	public boolean isEstablisherOfCircle(int userId, int circleId) {
		// TODO Auto-generated method stub
		return circleDao.isEstablisherOfCircle(userId, circleId);
	}

}
