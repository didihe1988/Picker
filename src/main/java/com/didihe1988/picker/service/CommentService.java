package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Comment;

public interface CommentService {
	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public Comment getCommentById(int id);

	public boolean isCommentExists(Comment comment);

	public int getLatestCommentIdByUserId(int id);

	public boolean checkDeleteValidation(int userId, int commentId);
	
	public List<Comment> getCommentListByQuestionId(int id);

	public List<Comment> getCommentListByAnswerId(int id);

}
