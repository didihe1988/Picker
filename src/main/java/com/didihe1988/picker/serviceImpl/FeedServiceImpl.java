package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.service.FeedService;

@Service
@Transactional
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedDao feedDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FollowDao followDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private RelatedImageDao relatedImageDao;

	@Autowired
	private BookDao bookDao;

	@Override
	public boolean checkOperateValidation(int userId, int feedId) {
		// TODO Auto-generated method stub
		return feedDao.checkOperateValidation(userId, feedId);
	}

	@Override
	public Feed getFeedById(int id) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedById(id);
	}

	@Override
	public int addFeed(Feed feed) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		int status = feedDao.addFeed(feed);
		if (status == -1) {
			return Status.EXISTS;
		}

		/*
		 * User questionNum++
		 */
		// userDao.incrementNum(Constant.QUESTION_NUM, question.getAskerId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteFeed(Feed feed, int userId) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		if (!feedDao.checkOperateValidation(userId, feed.getId())) {
			return Status.INVALID;
		}
		feedDao.deleteFeed(feed);
		return Status.SUCCESS;
	}

	@Override
	public int deleteFeedById(int feedId, int userId) {
		// TODO Auto-generated method stub
		if (!feedDao.checkOperateValidation(userId, feedId)) {
			return Status.INVALID;
		}
		feedDao.deleteFeedById(feedId);
		return Status.SUCCESS;
	}

	@Override
	public int updateFeed(Feed feed, int userId) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		if (!feedDao.checkOperateValidation(userId, feed.getId())) {
			return Status.INVALID;
		}
		int status = feedDao.updateFeed(feed);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Feed> getFeedListByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListByBookId(bookId, type);
	}

	@Override
	public List<Feed> getFeedListByUserId(int userId, int type) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListByUserId(userId, type);
	}

	@Override
	public int getLatestFeedByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		return feedDao.getLatestFeedByBookId(bookId, type);
	}

	@Override
	public boolean isFeedExistsById(int id) {
		// TODO Auto-generated method stub
		return feedDao.isFeedExistsById(id);
	}

	@Override
	public FeedDp getFeedDpByFeedId(int id, int curUserId) {
		// TODO Auto-generated method stub
		Feed feed = getFeedById(id);
		return getFeedDpByFeed(feed, curUserId);
	}

	private FeedDp getFeedDpByFeed(Feed feed, int curUserId) {
		User user = userDao.queryUserById(feed.getUserId());
		boolean isFavorite, isFollow;
		switch (feed.getType()) {
		case Feed.TYPE_NOTE:
			isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
					feed.getId(), Favorite.FAVORITE_NOTE);
			isFollow = false;
			break;
		case Feed.TYPE_QUESTION:
		default:
			isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
					feed.getId(), Favorite.FAVORITE_QUESTION);
			isFollow = followDao.isFollowExistsByKey(Follow.FOLLOW_QUESTION,
					curUserId, feed.getId());
			break;
		}

		String bookName = bookDao.queryBookById(feed.getBookId()).getBookName();
		FeedDp feedDp = new FeedDp(feed, bookName, user.getUsername(),
				user.getAvatarUrl(), isFollow, isFavorite,
				getImageUrlsFromFeed(feed));
		return feedDp;
	}

	private List<FeedDp> getFeedDpList(List<Feed> feedList, int curUserId) {
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	private List<String> getImageUrlsFromFeed(Feed feed) {

		List<RelatedImage> relatedImages = relatedImageDao
				.queryRelatedImagesByKey(feed.getId(),
						RelatedImage.getTypeFromObject(feed));
		List<String> list = new ArrayList<String>();
		if ((relatedImages != null) && (relatedImages.size() != 0)) {
			for (RelatedImage relatedImage : relatedImages) {
				list.add(relatedImage.getImageUrl());
			}
		}
		return list;
	}

	@Override
	public List<FeedDp> getFeedDpListByUserId(int userId, int type,
			int curUserId) {
		// TODO Auto-generated method stub
		final List<Feed> feedList = getFeedListByUserId(userId, type);
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	@Override
	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId) {
		// TODO Auto-generated method stub
		final List<Feed> feedList = getFeedListByBookId(bookId, type);
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	@Override
	public List<Feed> getFeedListForBrowse(int bookId) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListForBrowse(bookId);
	}

	@Override
	public List<FeedDp> getFeedDpListForBrowse(int bookId, int curUserId) {
		// TODO Auto-generated method stub
		final List<Feed> feedList = getFeedListForBrowse(bookId);
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	@Override
	public List<FeedDp> search(String string, int type, int curUserId) {
		// TODO Auto-generated method stub
		return getFeedDpList(feedDao.search(string, type), curUserId);
	}

	@Override
	public List<Feed> search(String string, int type) {
		// TODO Auto-generated method stub
		return feedDao.search(string, type);
	}

	@Override
	public List<NoteJson> getNoteJsons(int userId, int page) {
		// TODO Auto-generated method stub
		List<Feed> noteList = feedDao.queryFeedListByUserId(userId,
				Feed.TYPE_NOTE, page);
		List<NoteJson> list = new ArrayList<NoteJson>();
		for (Feed feed : noteList) {
			list.add(feed.toNoteJson());
		}
		return list;
	}

}
