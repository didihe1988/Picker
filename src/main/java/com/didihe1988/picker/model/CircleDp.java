package com.didihe1988.picker.model;

import java.util.Date;

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
		super(circle.getId(), circle.getName(), circle.getEstablishTime(),
				circle.getEstablisherId(), circle.getDescribe(), circle
						.getMemberNum());
		this.memberJoinTime = memberJoinTime;
	}

}
