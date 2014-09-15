package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.model.Feed;

public interface FeedDao extends OperateValidation, NumOperation {
	public Feed queryFeedById(int id);

	public int addFeed(Feed feed);

	public int deleteFeed(Feed feed);

	public int deleteFeedById(int id);

	public int updateFeed(Feed feed);

	public List<Feed> queryFeedListByBookId(int bookId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type);

	public int getLatestFeedByBookId(int bookId, int type);

	public boolean isFeedExistsById(int id);
}
