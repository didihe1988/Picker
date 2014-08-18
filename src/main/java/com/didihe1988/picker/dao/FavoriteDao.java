package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.model.Favorite;

public interface FavoriteDao extends DeleteValidation{
	public int addFavorite(Favorite favorite);

	public int deleteFavorite(Favorite favorite);

	public boolean isFavoriteExists(Favorite favorite);
	
	public boolean isFavoriteExists(int userId,int commentId);
	
	public List<Favorite> queryFavoriteListByUserId(int userId);
}
