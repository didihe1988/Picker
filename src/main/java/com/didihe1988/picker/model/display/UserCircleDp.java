package com.didihe1988.picker.model.display;

import java.util.List;

import com.didihe1988.picker.model.Circle;

public class UserCircleDp extends UserDp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Circle> circleList;

	public List<Circle> getCircleList() {
		return circleList;
	}

	public void setCircleList(List<Circle> circleList) {
		this.circleList = circleList;
	}

	public UserCircleDp() {

	}

	public UserCircleDp(UserDp userDp, List<Circle> circleList) {
		super(userDp);
		this.circleList = circleList;
	}

}
