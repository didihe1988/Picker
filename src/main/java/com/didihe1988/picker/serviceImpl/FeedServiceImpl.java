package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.FeedDp;
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

	@Override
	public boolean checkOperateValidation(int userId, int feedId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Feed getFeedById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		FeedDp feedDp = new FeedDp(feed, user.getUsername(),
				user.getAvatarUrl(), isFollow, isFavorite);
		return feedDp;
	}

	@Override
	public List<FeedDp> getFeedDpListByUserId(int userId, int type,
			int curUserId) {
		// TODO Auto-generated method stub
		List<Feed> feedList = getFeedListByUserId(userId, type);
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	@Override
	public List<FeedDp> getFeedDpListByBookId(int bookId, int type, int curUserId) {
		// TODO Auto-generated method stub
		List<Feed> feedList = getFeedListByBookId(bookId, type);
		List<FeedDp> list = new ArrayList<FeedDp>();
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

}
