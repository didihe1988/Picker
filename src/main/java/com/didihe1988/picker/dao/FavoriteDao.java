package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.model.Favorite;

public interface FavoriteDao extends DeleteValidation {
	public int addFavorite(Favorite favorite);

	public int deleteFavorite(Favorite favorite);

	public boolean isFavoriteExistsByKey(int userId, int objectId, int type);

	public boolean isFavoriteExists(Favorite favorite);

	public List<Favorite> queryFavoriteListByUserId(int userId);
}
