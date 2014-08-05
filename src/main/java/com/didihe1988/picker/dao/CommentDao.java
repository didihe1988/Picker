package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Comment;

public interface CommentDao extends NumOperation, DeleteValidation {
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExists(Comment comment);

	public List<Comment> queryCommentListByQuestionId(int id);

	public List<Comment> queryCommentListByAnswerId(int id);

	public int getLatestCommentIdByUserId(int id);

}