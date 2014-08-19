package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.CircleDao;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.service.CircleService;

@Service
@Transactional
public class CircleServiceImpl implements CircleService {
	@Autowired
	private CircleDao circleDao;

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
		return Status.SUCCESS;
	}

	@Override
	public int deleteCirvle(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return Status.NULLPOINTER;
		}
		// ɾ��֮ǰȷ���Ƿ����ɾ��
		if (!circleDao.checkDeleteValidation(circle.getEstablisherId(),
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
	public boolean isCircleExists(Circle circle) {
		// TODO Auto-generated method stub
		if (circle == null) {
			return false;
		}
		return circleDao.isCircleExists(circle);
	}

	@Override
	public Circle getCircleById(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryCircleById(id);
	}

	@Override
	public List<Circle> getCircleListByEstablisherId(int id) {
		// TODO Auto-generated method stub
		return circleDao.queryCircleListByEstablisherId(id);
	}

	@Override
	public boolean checkDeleteValidation(int establisherId, int circleId) {
		// TODO Auto-generated method stub
		return circleDao.checkDeleteValidation(establisherId, circleId);
	}

}