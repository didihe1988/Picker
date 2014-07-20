package com.didihe1988.picker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.service.FavoriteService;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Override
	public int incrementCommentFavorite(int commentId, int userId) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.queryCommentById(commentId);
		if (comment == null) {
			return Status.NOT_EXISTS;
		}
		// int userId = comment.getProducerId();
		Favorite favorite = new Favorite(commentId, userId);
		if (!favoriteDao.isFavoriteExists(favorite)) {
			userDao.incrementFavoriteNum(userId);
			commentDao.incrementFavoriteNum(commentId);
			favoriteDao.addFavorite(favorite);
		}
		return Status.SUCCESS;
	}

	@Override
	public int decrementCommentFavorite(int commentId, int userId) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.queryCommentById(commentId);
		if (comment == null) {
			return Status.NOT_EXISTS;
		}
		// int userId = comment.getProducerId();
		Favorite favorite = new Favorite(commentId, userId);
		if (favoriteDao.isFavoriteExists(favorite)) {
			userDao.decrementFavoriteNum(userId);
			commentDao.decrementFavoriteNumk(commentId);
			favoriteDao.deleteFavorite(favorite);
		}
		return Status.SUCCESS;
	}

}
