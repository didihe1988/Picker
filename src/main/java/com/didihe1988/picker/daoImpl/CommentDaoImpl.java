package com.didihe1988.picker.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.model.Comment;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Comment queryCommentById(int id) {
		// TODO Auto-generated method stub
		return (Comment) getCurrentSession().get(Comment.class, id);
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		if (isCommentExists(comment)) {
			return -1;
		}
		getCurrentSession().save(comment);
		return 1;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		if (!isCommentExists(comment)) {
			return -1;
		}
		getCurrentSession().delete(comment);
		return 1;
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		if (!isCommentExists(comment)) {
			return -1;
		}
		getCurrentSession().update(comment);
		return 1;
	}

	@Override
	public boolean isCommentExists(Comment comment) {
		// TODO Auto-generated method stub
		if(comment==null)
		{
			return false;
		}
		String hql = "select count(*) from Comment c where c.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, comment.getId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

}
