package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
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
		if (comment == null) {
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

	@Override
	public List<Comment> queryCommentListByQuestionId(int id) {
		// TODO Auto-generated method stub
		return queryCommentListByCommentedId(id, Comment.COMMENT_QUESTION);
	}

	@Override
	public List<Comment> queryCommentListByAnswerId(int id) {
		// TODO Auto-generated method stub
		return queryCommentListByCommentedId(id, Comment.COMMENT_ANSWER);
	}

	@SuppressWarnings("unchecked")
	private List<Comment> queryCommentListByCommentedId(int commentedId,
			int type) {
		String hql = "from Comment as c where c.commentedId=? and c.type=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, commentedId);
		query.setInteger(1, type);
		return query.list();
	}

	@Override
	public int getLatestCommentIdByUserId(int id) {
		// TODO Auto-generated method stub
		String hql = "select max(c.id) from Comment as c where c.producerId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Integer) query.uniqueResult();
	}

	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Comment as c set c." + property + "=c." + property
				+ "+1 and where c.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Comment as c set c." + property + "=c." + property
				+ "-1 and where c.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();

	}

	@Override
	public boolean checkDeleteValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Comment as c where c.producerId =? and f.id=?";
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
