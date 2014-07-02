package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.mapper.CommentMapper;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BookDao bookDao;

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
		bookDao.incrementComment(comment.getBookId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		int status = commentDao.deleteComment(comment);
		if (status == -1) {
			return Status.NOT_EXISTS;
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
	public List<Comment> getCommentByBookId(int bookId) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentByBookId(bookId);
	}

}
