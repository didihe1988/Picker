package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.NoteDao;
import com.didihe1988.picker.dao.QuestionDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.CommentDp;
import com.didihe1988.picker.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	/*
	 * @Autowired private BookDao bookDao;
	 */
	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		int status = commentDao.addComment(comment);
		if (status == -1) {
			return Status.EXISTS;
		}
		// Í¼ÊéµÄcommentNum++
		// bookDao.incrementComment(comment.getBookId());

		// commentNum++
		int type = comment.getType();
		if (type == Comment.COMMENT_QUESTION) {
			questionDao.incrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		} else if (type == Comment.COMMENT_ANSWER) {
			answerDao.incrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		} else if (type == Comment.COMMENT_NOTE) {
			noteDao.incrementNum(Constant.COMMENT_NUM, comment.getCommentedId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		if (!commentDao.checkDeleteValidation(comment.getProducerId(),
				comment.getId())) {
			return Status.INVALID;
		}
		commentDao.deleteComment(comment);

		// commentNum--
		if (comment.getType() == Comment.COMMENT_QUESTION) {
			questionDao.decrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		} else if (comment.getType() == Comment.COMMENT_ANSWER) {
			answerDao.decrementNum(Constant.COMMENT_NUM,
					comment.getCommentedId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return Status.NULLPOINTER;
		}
		int status = commentDao.updateComment(comment);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public Comment getCommentById(int id) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentById(id);
	}

	@Override
	public boolean isCommentExists(Comment comment) {
		// TODO Auto-generated method stub
		if (comment == null) {
			return false;
		}
		return commentDao.isCommentExists(comment);
	}

	@Override
	public int getLatestCommentIdByUserId(int id) {
		// TODO Auto-generated method stub
		return commentDao.getLatestCommentIdByUserId(id);
	}

	@Override
	public boolean checkDeleteValidation(int userId, int commenetId) {
		// TODO Auto-generated method stub
		return commentDao.checkDeleteValidation(userId, commenetId);
	}

	@Override
	public List<Comment> getCommentListByCommentedId(int commentedId, int type) {
		// TODO Auto-generated method stub
		return commentDao.queryCommentListByCommentedId(commentedId, type);
	}

	@Override
	public CommentDp getCommentDpByCommentId(int id) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.queryCommentById(id);
		String producerName = userDao.queryUserById(id).getUsername();
		int commentedId = comment.getCommentedId();
		String commentedName = "";
		if (comment.getType() == Comment.COMMENT_ANSWER) {
			commentedName = answerDao.queryAnswerById(commentedId).getContent();
		} else if (comment.getType() == Comment.COMMENT_QUESTION) {
			commentedName = questionDao.queryQuestionById(commentedId)
					.getTitle();
		} else if (comment.getType() == Comment.COMMENT_NOTE) {
			commentedName = noteDao.queryNoteById(commentedId).getTitle();
		}
		return new CommentDp(comment, producerName, commentedName);
	}
}
