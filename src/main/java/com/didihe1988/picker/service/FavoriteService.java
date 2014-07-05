package com.didihe1988.picker.service;

public interface FavoriteService {
	public int incrementCommentFavorite(int commentId,int userId);
	public int decrementCommentFavorite(int commentId,int userId);
}
