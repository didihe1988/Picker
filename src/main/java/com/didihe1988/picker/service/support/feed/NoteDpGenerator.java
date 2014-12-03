package com.didihe1988.picker.service.support.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.FeedDp;

@Service
@Transactional
public class NoteDpGenerator implements FeedDpGenerator {
	public NoteDpGenerator() {

	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Override
	public FeedDp getFeedDpfromFeed(Feed feed, int curUserId) {
		// TODO Auto-generated method stub
		User user = userDao.queryModelById(feed.getUserId());

		boolean isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
				feed.getId(), Favorite.FAVORITE_NOTE);
		FeedDp feedDp = new FeedDp(feed, user.getUsername(),
				user.getAvatarUrl(), false, isFavorite);
		return feedDp;
	}

	@Override
	public void completeFeedDp(FeedDp feedDp, int curUserId) {
		// TODO Auto-generated method stub
		boolean isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
				feedDp.getId(), Favorite.FAVORITE_NOTE);
		feedDp.setFavorite(isFavorite);
		feedDp.setFollow(false);
	}

}
