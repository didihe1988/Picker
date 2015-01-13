package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.common.DaoOrder;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.model.json.QuestionJson;
import com.didihe1988.picker.service.interfaces.SearchService;

public interface FeedService {
	public boolean checkOperateValidation(int userId, int feedId);

	public Feed getFeedById(int id);

	public FeedDp getFeedDpByFeedId(int id, int curUserId);
	
	public AttachmentFeedDp getAttFeedDpByFeedId(int id);

	public int addFeed(Feed feed);

	public int deleteFeed(Feed feed, int userId);

	public int deleteFeedById(int feedId, int userId);

	public int updateFeed(Feed feed, int userId);

	public int getLatestFeedByBookId(int bookId, int type);

	public boolean isFeedExistsById(int id);
	

	/*
	 * ---getFeedList starts---
	 */
	public List<Feed> getFeedListByBookId(int bookId, int type);

	public List<Feed> getLimitedFeedListByBookId(int bookId, int type, int limit);

	public List<Feed> getFeedListByUserId(int userId, int type);

	public List<FeedDp> getFeedDpListByUserId(int userId, int type,
			int curUserId);

	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId);

	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId,DaoOrder order);

	public List<FeedDp> getLimitedFeedDpListByBookId(int bookId, int type,
			int curUserId, int limit);

	public List<FeedDp> getLimitedFeedDpListByBookId(int bookId, int type,
			int curUserId, int limit,DaoOrder order);
	
	public List<AttachmentFeedDp> getAttFeedDpsByBookId(int bookId);
	
	public List<AttachmentFeedDp> getAttFeedDpsByBookId(int bookId,DaoOrder order);
	
	public List<AttachmentFeedDp> getLimitedAttFeedDpsByBookId(int bookId,int limit);
	
	public List<AttachmentFeedDp> getLimitedAttFeedDpsByBookId(int bookId,int limit,DaoOrder order);

	public List<Feed> getFeedListForBrowse(int bookId);

	public List<FeedDp> getFeedDpListForBrowse(int bookId, int curUserId);

	public List<FeedDp> search(String string, int type, int curUserId);

	public List<Feed> search(String string, int type);

	public List<NoteJson> getNoteJsons(int userId, int page);

	public List<QuestionJson> getQuestoinJsons(int userId, int page);

	public List<Feed> getFeedListByPage(int bookId, int page);

	public List<Feed> getFeedListByChapterRange(int bookId,
			ChapterRange chapterRange);
	
	

	/*
	 * ---getFeedList ends---
	 */
}
