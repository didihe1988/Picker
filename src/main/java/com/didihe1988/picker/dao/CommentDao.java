package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.OperateValidation;
import com.didihe1988.picker.dao.daoInterface.NumOperation;
import com.didihe1988.picker.model.Comment;

public interface CommentDao extends NumOperation, OperateValidation {
	public Comment queryCommentById(int id);

	public int addComment(Comment comment);

	public int deleteComment(Comment comment);

	public int updateComment(Comment comment);

	public boolean isCommentExistsById(int id);

	public boolean isCommentExistsByKey(int producerId, int commentedId,
			int type);

	public List<Comment> queryCommentListByCommentedId(int commentedId, int type);

	public int getLatestCommentIdByUserId(int id);

}