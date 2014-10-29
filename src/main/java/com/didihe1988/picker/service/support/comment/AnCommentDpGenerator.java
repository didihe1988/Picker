package com.didihe1988.picker.service.support.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.dp.CommentDp;

/*
 * CommentDpGenerator for Answer type
 */
@Service
@Transactional
public class AnCommentDpGenerator implements CommentDpGenerator {
	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Override
	public void completeCommentDp(CommentDp commentDp, int curUserId) {
		// TODO Auto-generated method stub
		int commentedId = commentDp.getCommentedId();
		String commentedName = answerDao.queryAnswerById(commentedId)
				.getContent();
		//commentDp.setCommentedName(commentedName);
		commentDp.setFavorite(favoriteDao.isFavoriteExistsByKey(curUserId,
				commentDp.getId(), Favorite.FAVORITE_COMMENT));
	}
}
