package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Comment;

public interface CommentDao {
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExists(Comment comment);

	public List<Comment> queryCommentByBookId(int id);

	public int incrementFavoriteNum(int id);

	public int decrementFavoriteNumk(int id);
	
	public int getLatestCommentIdByUserId(int id);
}
