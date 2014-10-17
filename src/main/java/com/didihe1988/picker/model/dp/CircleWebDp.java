package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.utils.DateUtils;

public class CircleWebDp extends Circle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String memberJoinTime;

	// establishTime
	private String strDate;

	public String getMemberJoinTime() {
		return memberJoinTime;
	}

	public void setMemberJoinTime(String memberJoinTime) {
		this.memberJoinTime = memberJoinTime;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public CircleWebDp() {

	}

	public CircleWebDp(Circle circle, String memberJoinTime) {
		/*
		 * È¥µô password
		 */
		super(circle);
		this.strDate = DateUtils.getDate(circle.getEstablishTime());
		this.memberJoinTime = memberJoinTime;
	}

}
