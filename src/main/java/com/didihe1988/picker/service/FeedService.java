package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;

public interface FeedService {
	public boolean checkOperateValidation(int userId, int feedId);

	public Feed getFeedById(int id);

	public FeedDp getFeedDpByFeedId(int id, int curUserId);

	public int addFeed(Feed feed);

	public int deleteFeed(Feed feed, int userId);

	public int deleteFeedById(int feedId, int userId);

	public int updateFeed(Feed feed, int userId);

	public List<Feed> getFeedListByBookId(int bookId, int type);

	public List<Feed> getFeedListByUserId(int userId, int type);

	public List<FeedDp> getFeedDpListByUserId(int userId, int type,
			int curUserId);

	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId);

	public int getLatestFeedByBookId(int bookId, int type);

	public boolean isFeedExistsById(int id);

	public List<Feed> getFeedListForBrowse(int bookId);

	public List<FeedDp> getFeedDpListForBrowse(int bookId, int curUserId);

	public List<FeedDp> search(String string, int curUserId);

	public List<FeedDp> search(String string, int type, int curUserId);
}
