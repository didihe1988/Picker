package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.dao.daoInterface.SearchOperation;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;

public interface FeedDao extends OperateValidation, NumOperation,
		SearchOperation<Feed> {
	public Feed queryFeedById(int id);

	public int addFeed(Feed feed);

	public int deleteFeed(Feed feed);

	public int deleteFeedById(int id);

	public int updateFeed(Feed feed);

	public List<Feed> queryFeedListByBookId(int bookId, int type);

	public List<FeedDp> queryFeedDpListByBookId(int bookId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type);

	public List<FeedDp> queryFeedDpListByUserId(int userId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type, int page);

	public int getLatestFeedByBookId(int bookId, int type);

	public boolean isFeedExistsById(int id);

	public List<Feed> queryFeedListForBrowse(int bookId);

	public List<FeedDp> queryFeedDpListForBrowse(int bookId);

	public List<Feed> search(String string, int type);

}
