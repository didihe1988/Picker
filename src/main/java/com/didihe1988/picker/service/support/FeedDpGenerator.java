package com.didihe1988.picker.service.support;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;

public interface FeedDpGenerator {
	public FeedDp getFeedDpfromFeed(Feed feed, int curUserId);
}
