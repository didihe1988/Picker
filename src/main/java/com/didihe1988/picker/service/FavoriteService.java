package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Favorite;

public interface FavoriteService {
	public int incrementCommentFavorite(int commentId, int userId);

	public int decrementCommentFavorite(int commentId, int userId);

	public int incrementQuestionFavorite(int questionId, int userId);

	public int decrementQuestionFavorite(int questionId, int userId);

	public int incrementAnswerFavorite(int answerId, int userId);

	public int decrementAnswerFavorite(int answerId, int userId);
	
	public List<Favorite> getFavoriteListByUserId(int userId);
}
