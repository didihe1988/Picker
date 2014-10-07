package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dpInterface.IsFollow;

public class UserDp extends User implements IsFollow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean isFollow;

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

	public boolean getFollow() {
		return isFollow;
	}

	public UserDp() {

	}

	public UserDp(User user, boolean isFollow) {
		/*
		 * È¥µô password
		 */
		super(user.getId(), user.getUsername(), user.getEmail(), user
				.getLastVisit(), user.getRegisterTime(), user.getFavoriteNum(),
				user.getFollowNum(), user.getFollowOthersNum(), user
						.getQuestionNum(), user.getAnswerNum(), user
						.getNoteNum(), user.getCircleNum(), user.getBookNum(),
				user.getAvatarUrl(), user.getSignature());
		this.isFollow = isFollow;
	}

	public UserDp(UserDp userDp) {
		super(userDp.getId(), userDp.getUsername(), userDp.getEmail(), userDp
				.getLastVisit(), userDp.getRegisterTime(), userDp
				.getFavoriteNum(), userDp.getFollowNum(), userDp
				.getFollowOthersNum(), userDp.getQuestionNum(), userDp
				.getAnswerNum(), userDp.getNoteNum(), userDp.getCircleNum(),
				userDp.getBookNum(), userDp.getAvatarUrl(), userDp
						.getSignature());
		this.isFollow = userDp.isFollow();
	}

}
