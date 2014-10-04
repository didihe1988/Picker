package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Circle;

public class CircleDp extends Circle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isJoin;

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	public CircleDp(Circle circle, boolean isJoin) {
		super(circle);
		this.isJoin = isJoin;
	}

	public CircleDp() {

	}

}
