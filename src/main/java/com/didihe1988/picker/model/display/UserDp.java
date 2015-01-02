package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.interfaces.IsFollow;
import com.didihe1988.picker.utils.DateUtils;

public class UserDp extends User implements IsFollow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean isFollow;

	public String strDate;

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

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
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
		this.strDate = DateUtils.getDate(user.getRegisterTime());
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
