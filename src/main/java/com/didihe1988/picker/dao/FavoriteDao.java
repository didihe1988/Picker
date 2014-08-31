package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.model.Favorite;

public interface FavoriteDao extends OperateValidation {
	public int addFavorite(Favorite favorite);

	public int deleteFavorite(Favorite favorite);

	public boolean isFavoriteExistsByKey(int userId, int objectId, int type);

	public boolean isFavoriteExists(Favorite favorite);

	public List<Favorite> queryFavoriteListByUserId(int userId);
}
