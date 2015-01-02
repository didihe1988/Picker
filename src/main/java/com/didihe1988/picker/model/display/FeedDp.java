package com.didihe1988.picker.model.display;

import java.util.Date;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.interfaces.IsFavorite;
import com.didihe1988.picker.model.interfaces.IsFollow;
import com.didihe1988.picker.utils.DateUtils;

public class FeedDp extends Feed implements IsFavorite, IsFollow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String userName;

	protected String userAvatarUrl;

	protected boolean isFollow;

	protected boolean isFavorite;

	protected String strDate;

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

	public FeedDp(Feed feed, String userName, String userAvatarUrl,
			boolean isFollow, boolean isFavorite) {
		super(feed);
		this.strDate = DateUtils.getDate(feed.getDate());
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
	}

	public FeedDp(Feed feed, String userName, String userAvatarUrl) {
		super(feed);
		this.strDate = DateUtils.getDate(feed.getDate());
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = false;
		this.isFavorite = false;
	}

	public FeedDp(int id, int bookId, int userId, String title, String content,
			String brief, Date date, int page, int type, boolean isPublic,
			int favoriteNum, int answerNum, int commentNum, int followNum,
			String userName, String userAvatarUrl, boolean isFollow,
			boolean isFavorite, String strDate) {
		super(id, bookId, userId, title, content, brief, date, page, type,
				isPublic, favoriteNum, answerNum, commentNum, followNum);
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
		this.strDate = strDate;
	}

	@Override
	public String toString() {
		return "FeedDp [userName=" + userName + ", userAvatarUrl="
				+ userAvatarUrl + ", isFollow=" + isFollow + ", isFavorite="
				+ isFavorite + ", strDate=" + strDate + ", id=" + id
				+ ", bookId=" + bookId + ", userId=" + userId + ", title="
				+ title + ", content=" + content + ", brief=" + brief
				+ ", date=" + date + ", page=" + page + ", type=" + type
				+ ", isPublic=" + isPublic + ", favoriteNum=" + favoriteNum
				+ ", answerNum=" + answerNum + ", commentNum=" + commentNum
				+ ", followNum=" + followNum + "]";
	}

}
