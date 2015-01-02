package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.display.CommentDp;

public interface CommentDao extends NumOperationDao<Comment>, OperateValidation {
	public Comment queryModelById(int id);

	public int deleteComment(Comment comment);

	public boolean isCommentExistsByKey(int producerId, int commentedId,
			int type);

	public List<Comment> queryCommentListByCommentedId(int commentedId, int type);

	public List<CommentDp> queryCommentDpListByCommentedId(int commentedId,
			int type);

	public int getLatestCommentIdByUserId(int id);

}