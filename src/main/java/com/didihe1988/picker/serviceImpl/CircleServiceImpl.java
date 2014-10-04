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
import com.didihe1988.picker.model.dp.CircleDp;
import com.didihe1988.picker.service.CircleService;

@Service
@Transactional
public class CircleServiceImpl implements CircleService {
	@Autowired
	private CircleDao circleDao;

	@Autowired
	private CircleMemberDao circleMemberDao;

	@Override
	public int addCircle(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return Status.NULLPOINTER;
		}
		int status = circleDao.addCircle(circle);
		if (status == -1) {
			return Status.EXISTS;
		}
		/*
		 * int circleId = getLatestCircleIdByEstablisherId(circle
		 * .getEstablisherId()); CircleMember circleMember = new
		 * CircleMember(circleId, circle.getEstablisherId());
		 * circleMemberDao.addCircleMember(circleMember);
		 */
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
		int status = circleDao.updateCircle(circle);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isCircleExistsById(int id) {
		// TODO Auto-generated method stub
		return circleDao.isCircleExistsById(id);
	}

	@Override
	public boolean isCircleExistsByName(String name) {
		// TODO Auto-generated method stub
		return circleDao.isCircleExistsByName(name);
	}

	@Override
	public Circle getCircleById(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryCircleById(id);
	}

	@Override
	public CircleDp getCircleDpById(int id, int curUserId) {
		// TODO Auto-generated method stub
		Circle circle = circleDao.queryCircleById(id);
		return new CircleDp(circle, circleMemberDao.isUserInCircle(curUserId,
				id));
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

}
