package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Circle;

public interface CircleDao extends DeleteValidation, NumOperation {
	public Circle queryCircleById(int id);

	public int addCircle(Circle circle);

	public int deleteCircle(Circle circle);

	public int updateCircle(Circle circle);

	public boolean isCircleExists(Circle circle);

	public List<Circle> queryCircleListByEstablisherId(int id);

	public boolean isEstablisherOfCircle(int userId, int circleId);

	public int queryLatestCircleIdByEstablisherId(int id);

}
