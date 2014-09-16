package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dpInterface.IsFavorite;
import com.didihe1988.picker.model.dpInterface.IsFollow;

public class FeedDp extends Feed implements IsFavorite, IsFollow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String userAvatarUrl;

	private boolean isFollow;

	private boolean isFavorite;

	private String imageUrl;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public FeedDp() {

	}

	public FeedDp(Feed feed, String userName, String userAvatarUrl,
			boolean isFollow, boolean isFavorite, String imageUrl) {
		super(feed);
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
		this.imageUrl = imageUrl;
	}

}
