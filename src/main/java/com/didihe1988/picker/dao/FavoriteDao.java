package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.validation.DeleteValidation;

public interface FavoriteDao extends DeleteValidation{
	public int addFavorite(Favorite favorite);

	public int deleteFavorite(Favorite favorite);

	public boolean isFavoriteExists(Favorite favorite);
	
	public boolean isFavoriteExists(int userId,int commentId);
}
