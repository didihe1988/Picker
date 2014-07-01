package com.didihe1988.picker.dao;

import com.didihe1988.picker.model.Comment;

public interface CommentDao {
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExists(Comment comment);
}
