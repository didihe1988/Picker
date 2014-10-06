package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.User;

public class CircleMemberDp extends User {
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

	public CircleMemberDp(User user, Date memberJoinTime) {
		/*
		 * È¥µô password
		 */
		super(user.getId(), user.getUsername(), user.getEmail(), user
				.getLastVisit(), user.getRegisterTime(), user.getFavoriteNum(),
				user.getFollowNum(), user.getFollowOthersNum(), user
						.getQuestionNum(), user.getAnswerNum(), user
						.getNoteNum(), user.getCircleNum(), user.getBookNum(),
				user.getAvatarUrl(), user.getSignature());
		this.memberJoinTime = memberJoinTime;
	}

}
