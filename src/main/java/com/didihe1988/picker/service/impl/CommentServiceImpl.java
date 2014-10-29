package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.CommentDp;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.service.support.comment.AnCommentDpGenerator;
import com.didihe1988.picker.service.support.comment.CommentDpGenerator;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	/*
	 * @Autowired private BookDao bookDao;
	 */
	@Autowired
	private FeedDao feedDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	@Qualifier("anCommentDpGenerator")
	private CommentDpGenerator anCommentDpGenerator;

	@Autowired
	@Qualifier("feCommentDpGenerator")
	private CommentDpGenerator feCommentDpGenerator;

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		int status = commentDao.addComment(comment);
		if (status == -1) {
			return Status.EXISTS;
		}
		// Í¼ÊéµÄcommentNum++
		// bookDao.incrementComment(comment.getBookId());

		// commentNum++
		int type = comment.getType();
		if ((type == Comment.COMMENT_QUESTION)
				|| (type == Comment.COMMENT_NOTE)) {
			feedDao.incrementNum(Constant.COMMENT_NUM, comment.getCommentedId());
		} else if (type == Comment.COMMENT_ANSWER) {
			answerDao.incrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteComment(Comment comment, int userId) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		if (!commentDao.checkOperateValidation(userId, comment.getId())) {
			return Status.INVALID;
		}
		commentDao.deleteComment(comment);

		// commentNum--
		if ((comment.getType() == Comment.COMMENT_QUESTION)
				|| (comment.getType() == Comment.COMMENT_NOTE)) {
			feedDao.decrementNum(Constant.COMMENT_NUM, comment.getCommentedId());
		} else if (comment.getType() == Comment.COMMENT_ANSWER) {
			answerDao.decrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteCommentById(int id, int userId) {
		// TODO Auto-generated method stub
		return deleteComment(commentDao.queryCommentById(id), userId);
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		int status = commentDao.updateComment(comment);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public Comment getCommentById(int id) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentById(id);
	}

	@Override
	public boolean isCommentExistsById(int id) {
		// TODO Auto-generated method stub
		return commentDao.isCommentExistsById(id);
	}

	@Override
	public boolean isCommentExistsByKey(int producerId, int commentedId,
			int type) {
		// TODO Auto-generated method stub
		return commentDao.isCommentExistsByKey(producerId, commentedId, type);
	}

	@Override
	public int getLatestCommentIdByUserId(int id) {
		// TODO Auto-generated method stub
		return commentDao.getLatestCommentIdByUserId(id);
	}

	@Override
	public boolean checkDeleteValidation(int userId, int commenetId) {
		// TODO Auto-generated method stub
		return commentDao.checkOperateValidation(userId, commenetId);
	}

	@Override
	public List<Comment> getCommentListByCommentedId(int commentedId, int type) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentListByCommentedId(commentedId, type);
	}

	@Override
	public CommentDp getCommentDpByCommentId(int id, int userId) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.queryCommentById(id);
		return getCommentDpByComment(comment, userId);
	}

	private CommentDp getCommentDpByComment(Comment comment, int userId) {
		// TODO Auto-generated method stub
		User user = userDao.queryUserById(comment.getProducerId());
		int commentedId = comment.getCommentedId();
		String commentedName = "";
		if (comment.getType() == Comment.COMMENT_ANSWER) {
			commentedName = answerDao.queryAnswerById(commentedId).getContent();
		} else if ((comment.getType() == Comment.COMMENT_QUESTION)
				|| (comment.getType() == Comment.COMMENT_NOTE)) {
			commentedName = feedDao.queryFeedById(commentedId).getTitle();
		}
		return new CommentDp(comment, user.getUsername(), commentedName,
				user.getAvatarUrl(), favoriteDao.isFavoriteExistsByKey(userId,
						comment.getId(), Favorite.FAVORITE_COMMENT));
	}

	@Override
	public List<CommentDp> getCommentDpListByCommentedId(int commentedId,
			int type, int userId) {
		// TODO Auto-generated method stub
		List<CommentDp> list = commentDao.queryCommentDpListByCommentedId(
				commentedId, type);
		CommentDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getCommentDpGeneratorByType(list.get(0).getType());
		}
		for (CommentDp commentDp : list) {
			generator.completeCommentDp(commentDp, userId);
		}
		return list;
	}

	private CommentDpGenerator getCommentDpGeneratorByType(int type) {
		if (type == Comment.COMMENT_ANSWER) {
			return this.anCommentDpGenerator;
		} else {
			return this.feCommentDpGenerator;
		}
	}

}
