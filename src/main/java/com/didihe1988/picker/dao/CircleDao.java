package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.Circle;

public interface CircleDao extends NumOperationDao<Circle>, OperateValidation,
		SearchOperation<Circle> {
	public Circle queryModelById(int id);

	public int deleteCircle(Circle circle);

	public boolean isCircleExistsByName(String name);

	public List<Circle> queryCircleListByEstablisherId(int id);

	public boolean isEstablisherOfCircle(int userId, int circleId);

	public int queryLatestCircleIdByEstablisherId(int id);

}
