package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dpInterface.IsFavorite;
import com.didihe1988.picker.model.dpInterface.IsFollow;

public class FeedDp extends Feed implements IsFavorite, IsFollow {
	private String userName;

	private String userAvatarUrl;

	private boolean isFollow;

	private boolean isFavorite;

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

	public FeedDp() {

	}

	public FeedDp(Feed feed, String userName, String userAvatarUrl,
			boolean isFollow, boolean isFavorite) {
		super(feed.getId(), feed.getBookId(), feed.getUserId(),
				feed.getTitle(), feed.getContent(), feed.getDate(), feed
						.getPage(), feed.getType(), feed.isPublic(), feed
						.getFavoriteNum(), feed.getAnswerNum(), feed
						.getCommentNum(), feed.getFollowNum());

		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
	}

}
