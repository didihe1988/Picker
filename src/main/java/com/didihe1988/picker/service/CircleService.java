package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.display.CircleDp;

public interface CircleService{
	public int addCircle(Circle circle);

	public int deleteCirvle(Circle circle);

	public int updateCircle(Circle circle);

	public boolean isCircleExistsById(int id);

	public boolean isCircleExistsByName(String name);

	public Circle getCircleById(int id);

	public CircleDp getCircleDpById(int id, int curUserId);

	public List<Circle> getCircleListByEstablisherId(int id);

	public boolean checkDeleteValidation(int establisherId, int circleId);

	public int getLatestCircleIdByEstablisherId(int id);

	public List<Circle> search(String name,boolean isLimited);

	public boolean isEstablisherOfCircle(int userId, int circleId);

}
