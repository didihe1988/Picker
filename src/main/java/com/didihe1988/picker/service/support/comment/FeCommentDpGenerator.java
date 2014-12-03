package com.didihe1988.picker.service.support.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.dp.CommentDp;

@Service
@Transactional
public class FeCommentDpGenerator implements CommentDpGenerator {
	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private FeedDao feedDao;

	@Override
	public void completeCommentDp(CommentDp commentDp, int curUserId) {
		// TODO Auto-generated method stub
		int commentedId = commentDp.getCommentedId();
		String commentedName = feedDao.queryModelById(commentedId).getTitle();
		//commentDp.setCommentedName(commentedName);
		commentDp.setFavorite(favoriteDao.isFavoriteExistsByKey(curUserId,
				commentDp.getId(), Favorite.FAVORITE_COMMENT));

	}

}
