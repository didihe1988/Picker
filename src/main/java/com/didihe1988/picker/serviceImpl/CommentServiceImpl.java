package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.CommentService;
import com.didihe1988.picker.utils.HttpUtils;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	/*
	 * @Autowired private BookDao bookDao;
	 */
	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

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
		if (comment.getType() == Comment.COMMENT_QUESTION) {
			questionDao.incrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		} else if (comment.getType() == Comment.COMMENT_ANSWER) {
			answerDao.incrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		if (!commentDao.checkDeleteValidation(comment.getProducerId(),
				comment.getId())) {
			return Status.INVALID;
		}
		commentDao.deleteComment(comment);

		// commentNum--
		if (comment.getType() == Comment.COMMENT_QUESTION) {
			questionDao.decrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		} else if (comment.getType() == Comment.COMMENT_ANSWER) {
			answerDao.decrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		}
		return Status.SUCCESS;
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
	public boolean isCommentExists(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return false;
		}
		return commentDao.isCommentExists(comment);
	}

	@Override
	public int getLatestCommentIdByUserId(int id) {
		// TODO Auto-generated method stub
		return commentDao.getLatestCommentIdByUserId(id);
	}

	@Override
	public boolean checkDeleteValidation(int userId, int commenetId) {
		// TODO Auto-generated method stub
		return commentDao.checkDeleteValidation(userId, commenetId);
	}

	@Override
	public List<Comment> getCommentListByQuestionId(int id) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentListByQuestionId(id);
	}

	@Override
	public List<Comment> getCommentListByAnswerId(int id) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentListByAnswerId(id);
	}

}
