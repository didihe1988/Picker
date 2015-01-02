package com.didihe1988.picker.service.support.feed;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.FeedDp;

public interface FeedDpGenerator {
	public FeedDp getFeedDpfromFeed(Feed feed, int curUserId);
	
	/**
	 * @description add isFollow isFavorite
	 */
	public void completeFeedDp(FeedDp feedDp,int curUserId);
}
