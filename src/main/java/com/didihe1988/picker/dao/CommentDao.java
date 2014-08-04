package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.validation.DeleteValidation;

public interface CommentDao extends NumOperation,DeleteValidation{
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExists(Comment comment);

	public List<Comment> queryCommentByBookId(int id);

	public int getLatestCommentIdByUserId(int id);

}