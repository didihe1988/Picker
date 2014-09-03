package com.didihe1988.picker.model;

import com.didihe1988.picker.model.dpInterface.IsFollow;

public class UserDp extends User implements IsFollow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isFollow;

	@Override
	public boolean isFollow() {
		// TODO Auto-generated method stub
		return isFollow;
	}

	@Override
	public void setFollow(boolean value) {
		// TODO Auto-generated method stub
		this.isFollow = value;
	}

	public UserDp() {

	}

	public UserDp(User user, boolean isFollow) {
		super(user.getId(), user.getUsername(), user.getEmail(), user
				.getLastVisit(), user.getRegisterTime(), user.getFavoriteNum(),
				user.getFollowNum(), user.getFollowOthersNum(), user
						.getQuestionNum(), user.getAnswerNum(), user
						.getNoteNum(), user.getCircleNum(), user.getBookNum(),
				user.getAvatarUrl(), user.getSignature());
		this.isFollow = isFollow;
	}
	/*
	 * public UserDp(User user) { super(user.getId(), user.getUsername(),
	 * user.getEmail(), user .getLastVisit(), user.getRegisterTime(),
	 * user.getFavoriteNum(), user.getFollowNum(), user.getFollowOthersNum(),
	 * user .getQuestionNum(), user.getAnswerNum(), user .getNoteNum(),
	 * user.getCircleNum(), user.getBookNum(), user.getAvatarUrl(),
	 * user.getSignature()); this.isFollow = false; }
	 */

}
