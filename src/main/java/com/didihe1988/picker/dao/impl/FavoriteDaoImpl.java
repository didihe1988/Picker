package com.didihe1988.picker.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.model.Favorite;

@Repository
@Transactional
public class FavoriteDaoImpl implements FavoriteDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		if (isFavoriteExistsByKey(favorite.getUserId(), favorite.getObjectId(),
				favorite.getType())) {
			return -1;
		}
		getCurrentSession().save(favorite);
		return 1;
	}

	@Override
	public int deleteFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		if (!isFavoriteExistsByKey(favorite.getUserId(),
				favorite.getObjectId(), favorite.getType())) {
			return -1;
		}
		getCurrentSession().delete(favorite);
		return 1;
	}

	@Override
	public boolean isFavoriteExistsByKey(int userId, int objectId, int type) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Favorite f where f.userId =? and f.objectId=? and f.type=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, objectId);
		query.setInteger(2, type);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteFavorite(int objectId, int userId, int type) {
		// TODO Auto-generated method stub
		if (!isFavoriteExistsByKey(userId, objectId, type)) {
			return -1;
		}
		String hql = "delete Favorite where objectId=? and userId=? and type=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, objectId);
		query.setInteger(1, userId);
		query.setInteger(2, type);
		return query.executeUpdate();
	}

	@Override
	public boolean isFavoriteExists(Favorite favorite) {
		// TODO Auto-generated method stub
		return isFavoriteExistsByKey(favorite.getUserId(),
				favorite.getObjectId(), favorite.getType());
	}

	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Favorite as f where f.userId =? and f.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Favorite> queryFavoriteListByUserId(int userId) {
		// TODO Auto-generated method stub
		String hql = "from Favorite as f where f.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		return query.list();
	}

}
