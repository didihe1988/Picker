package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.dp.CommentDp;

public interface CommentService {
	public int addComment(Comment comment);

	public int deleteComment(Comment comment, int userId);

	public int deleteCommentById(int id, int userId);

	public int updateComment(Comment comment);

	public Comment getCommentById(int id);

	public boolean isCommentExistsById(int id);

	public boolean isCommentExistsByKey(int producerId, int commentedId,
			int type);

	public int getLatestCommentIdByUserId(int id);

	public boolean checkDeleteValidation(int userId, int commentId);

	public List<Comment> getCommentListByCommentedId(int commentedId, int type);

	public List<CommentDp> getCommentDpListByCommentedId(int commentedId,
			int type, int userId);

	public CommentDp getCommentDpByCommentId(int id, int userId);
}
