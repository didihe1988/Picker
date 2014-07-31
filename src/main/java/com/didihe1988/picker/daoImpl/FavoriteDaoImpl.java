package com.didihe1988.picker.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.validation.DeleteValidation;

@Repository
@Transactional
public class FavoriteDaoImpl implements FavoriteDao, DeleteValidation {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		if (isFavoriteExists(favorite)) {
			return -1;
		}
		getCurrentSession().save(favorite);
		return 1;
	}

	@Override
	public int deleteFavorite(Favorite favorite) {
		// TODO Auto-generated method stub
		if (!isFavoriteExists(favorite)) {
			return -1;
		}
		getCurrentSession().delete(favorite);
		return 1;
	}

	@Override
	public boolean isFavoriteExists(Favorite favorite) {
		// TODO Auto-generated method stub
		if (favorite == null) {
			return false;
		}
		String hql = "select count(*) from Favorite f where f.userId =? and f.commentId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, favorite.getUserId());
		query.setInteger(1, favorite.getCommentId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFavoriteExists(int userId, int commentId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Favorite f where f.userId =? and f.commentId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, commentId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkDeleteValidation(int ownerId, int objectId) {
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

}
