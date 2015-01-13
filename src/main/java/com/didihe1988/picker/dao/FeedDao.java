package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.common.DaoOrder;
import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.dao.interfaces.PageRelatedOperation;
import com.didihe1988.picker.dao.interfaces.SearchOperation;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.display.FeedDp;

public interface FeedDao extends NumOperationDao<Feed>, OperateValidation,
		SearchOperation<Feed>, PageRelatedOperation<Feed> {
	public Feed queryModelById(int id);

	public int deleteFeed(Feed feed);
	
	public int getLatestFeedByBookId(int bookId, int type);
	
	public AttachmentFeedDp queryAttFeedDpByFeedId(int id);

	/*
	 * ---queryFeedList starts---
	 */
	public List<Feed> queryFeedListByBookId(int bookId, int type);
	
	public List<Feed> queryLimitedFeedListByBookId(int bookId, int type,int limit);

	public List<FeedDp> queryFeedDpListByBookId(int bookId, int type);
	
	public List<FeedDp> queryFeedDpListByBookId(int bookId, int type,DaoOrder order);
	
	public List<FeedDp> queryLimitedFeedDpListByBookId(int bookId, int type,int limit);
	
	public List<FeedDp> queryLimitedFeedDpListByBookId(int bookId, int type,int limit,DaoOrder order);
	
	public List<AttachmentFeedDp> queryAttFeedDpsByBookId(int bookId,DaoOrder order);
	
	public List<AttachmentFeedDp> queryLimitedAttFeedDpsByBookId(int bookId,int limit,DaoOrder order);

	public List<Feed> queryFeedListByUserId(int userId, int type);

	public List<FeedDp> queryFeedDpListByUserId(int userId, int type);

	public List<Feed> queryFeedListByUserId(int userId, int type, int page);

	public List<Feed> queryFeedListForBrowse(int bookId);

	public List<FeedDp> queryFeedDpListForBrowse(int bookId);

	public List<Feed> search(String string, int type);
	
	/*
	 * ---queryFeedList ends---
	 */
}
