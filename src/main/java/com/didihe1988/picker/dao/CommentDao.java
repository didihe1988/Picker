package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.dp.CommentDp;

public interface CommentDao extends NumOperationDao, OperateValidation {
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExistsById(int id);

	public boolean isCommentExistsByKey(int producerId, int commentedId,
			int type);

	public List<Comment> queryCommentListByCommentedId(int commentedId, int type);

	public List<CommentDp> queryCommentDpListByCommentedId(int commentedId,
			int type);

	public int getLatestCommentIdByUserId(int id);

}