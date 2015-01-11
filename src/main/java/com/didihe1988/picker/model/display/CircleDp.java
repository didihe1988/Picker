package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.Circle;

public class CircleDp extends Circle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String establisherName;

	private boolean isJoin;

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	public String getEstablisherName() {
		return establisherName;
	}

	public void setEstablisherName(String establisherName) {
		this.establisherName = establisherName;
	}

	public CircleDp(Circle circle, boolean isJoin, String establisherName) {
		super(circle);
		this.isJoin = isJoin;
		this.establisherName = establisherName;
	}

	public CircleDp() {

	}

}
