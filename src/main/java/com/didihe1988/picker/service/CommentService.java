package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Comment;

public interface CommentService {
	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public Comment getCommentById(int id);

	public boolean isCommentExists(Comment comment);
	
	public List<Comment> getCommentByBookId(int bookId);
}
