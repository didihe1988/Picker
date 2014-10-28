package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.interfaces.IsFavorite;
import com.didihe1988.picker.model.interfaces.IsFollow;
import com.didihe1988.picker.utils.DateUtils;

public class FeedDp extends Feed implements IsFavorite, IsFollow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String bookName;
	
	private String userName;

	private String userAvatarUrl;

	private boolean isFollow;

	private boolean isFavorite;

	private String strDate;

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

	@Override
	public boolean isFavorite() {
		// TODO Auto-generated method stub
		return isFavorite;
	}

	@Override
	public void setFavorite(boolean value) {
		// TODO Auto-generated method stub
		this.isFavorite = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public FeedDp() {

	}

	public FeedDp(Feed feed,String userName,
			String userAvatarUrl, boolean isFollow, boolean isFavorite) {
		super(feed);
		this.strDate = DateUtils.getDate(feed.getDate());
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
	}

}
