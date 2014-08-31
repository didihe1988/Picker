package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.CircleDp;
import com.didihe1988.picker.model.QuestionDp;

public interface CircleService {
	public int addCircle(Circle circle);

	public int deleteCirvle(Circle circle);

	public int updateCircle(Circle circle);

	public boolean isCircleExists(Circle circle);

	public Circle getCircleById(int id);

	public List<Circle> getCircleListByEstablisherId(int id);

	public boolean checkDeleteValidation(int establisherId, int circleId);

	public int getLatestCircleIdByEstablisherId(int id);
	
}
