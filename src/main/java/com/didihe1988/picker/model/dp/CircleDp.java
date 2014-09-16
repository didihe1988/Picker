package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.Circle;

public class CircleDp extends Circle {
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

	public CircleDp() {

	}

	public CircleDp(Circle circle, Date memberJoinTime) {
		super(circle);
		this.memberJoinTime = memberJoinTime;
	}

}
