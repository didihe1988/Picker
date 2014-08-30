package com.didihe1988.picker.model;

public class UserDp extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDp() {

	}

	public UserDp(User user) {
		super(user.getId(), user.getUsername(), user.getEmail(), user
				.getLastVisit(), user.getRegisterTime(), user.getFavoriteNum(),
				user.getFollowNum(), user.getFollowOthersNum(), user
						.getQuestionNum(), user.getAnswerNum(), user
						.getNoteNum(), user.getCircleNum(), user.getBookNum(),
				user.getAvatarUrl(), user.getSignature());
	}

}
