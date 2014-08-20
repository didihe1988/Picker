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

	public int incrementNoteFavorite(int noteId, int userId);

	public int decrementNoteFavorite(int noteId, int userId);

	/**
	 * @description 用于功能:获得用户点过的赞 感觉是用户足迹的一种体现
	 */
	public List<Favorite> getFavoriteListByUserId(int userId);
}
