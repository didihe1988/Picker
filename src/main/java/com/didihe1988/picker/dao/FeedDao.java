package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.dao.interfaces.PageRelatedOperation;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.dp.FeedDp;

public interface FeedDao extends NumOperationDao<Feed>, OperateValidation,
		SearchOperation<Feed>, PageRelatedOperation<Feed> {
	public Feed queryFeedById(int id);

	public int deleteFeed(Feed feed);

	public int deleteFeedById(int id);

	public List<Feed> queryFeedListByBookId(int bookId, int type);

	public List<FeedDp> queryFeedDpListByBookId(int bookId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type);

	public List<FeedDp> queryFeedDpListByUserId(int userId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type, int page);

	public int getLatestFeedByBookId(int bookId, int type);

	public List<Feed> queryFeedListForBrowse(int bookId);

	public List<FeedDp> queryFeedDpListForBrowse(int bookId);

	public List<Feed> search(String string, int type);

}
