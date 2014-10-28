package com.didihe1988.picker.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.FeedDp;

@Service
@Transactional
public class QuestoinDpGenerator implements FeedDpGenerator {
	public QuestoinDpGenerator() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private FollowDao followDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Override
	public FeedDp getFeedDpfromFeed(Feed feed, int curUserId) {
		// TODO Auto-generated method stub
		User user = userDao.queryUserById(feed.getUserId());
		boolean isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
				feed.getId(), Favorite.FAVORITE_QUESTION);
		boolean isFollow = followDao.isFollowExistsByKey(
				Follow.FOLLOW_QUESTION, curUserId, feed.getId());
		FeedDp feedDp = new FeedDp(feed, user.getUsername(),
				user.getAvatarUrl(), isFollow, isFavorite);
		return feedDp;
	}

}
