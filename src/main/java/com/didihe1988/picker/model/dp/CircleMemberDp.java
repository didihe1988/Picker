package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.Circle;

public class CircleMemberDp extends UserDp {
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

	public CircleMemberDp() {

	}
	/*
	public CircleMemberDp(UserDp userDp, Date memberJoinTime) {
		super(userDp);
		this.memberJoinTime = memberJoinTime;
	}*/

}
