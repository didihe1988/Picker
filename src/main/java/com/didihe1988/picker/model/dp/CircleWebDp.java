package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.Circle;

public class CircleWebDp extends Circle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date memberJoinTime;

	public Date getMemberJoinTime() {
		return memberJoinTime;
	}

	public void setMemberJoinTime(Date memberJoinTime) {
		this.memberJoinTime = memberJoinTime;
	}

	public CircleWebDp() {

	}

	public CircleWebDp(Circle circle, Date memberJoinTime) {
		/*
		 * È¥µô password
		 */
		super(circle);
		this.memberJoinTime = memberJoinTime;
	}

}
