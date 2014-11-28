package com.didihe1988.picker.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.NumOperationDao;
import com.didihe1988.picker.dao.interfaces.OperateValidation;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.service.FavoriteService;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private FeedDao feedDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FavoriteDao favoriteDao;

	private Map<Integer, NumOperationDao> targetDaos;

	@PostConstruct
	public void initTargetDaos() {
		targetDaos = new HashMap<Integer, NumOperationDao>();
		targetDaos.put(Favorite.FAVORITE_ANSWER, answerDao);
		targetDaos.put(Favorite.FAVORITE_QUESTION, feedDao);
		targetDaos.put(Favorite.FAVORITE_NOTE, feedDao);
		targetDaos.put(Favorite.FAVORITE_COMMENT, commentDao);
	}

	/*
	 * 不可以像dao层一样用incrementXXXFavorite 因为操作不再是原子操作
	 */

	@Override
	public int incrementCommentFavorite(int commentId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * Comment comment = commentDao.queryCommentById(commentId); if (comment
		 * == null) { return Status.NOT_EXISTS; } //modelDao.isExistsById() //
		 * int userId = comment.getProducerId(); Favorite favorite = new
		 * Favorite(commentId, userId, Favorite.FAVORITE_COMMENT); if
		 * (!favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.incrementNum(Constant.FAVORITE_NUM, userId);
		 * commentDao.incrementNum(Constant.FAVORITE_NUM, commentId);
		 * favoriteDao.addFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.EXISTS; }
		 */
		return incModelFavorite(commentId, userId, Favorite.FAVORITE_COMMENT);
	}

	@Override
	public int decrementCommentFavorite(int commentId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * Comment comment = commentDao.queryCommentById(commentId); if (comment
		 * == null) { return Status.NOT_EXISTS; } // int userId =
		 * comment.getProducerId(); Favorite favorite = new Favorite(commentId,
		 * userId, Favorite.FAVORITE_COMMENT); if
		 * (favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.decrementNum(Constant.FAVORITE_NUM, userId);
		 * commentDao.decrementNum(Constant.FAVORITE_NUM, commentId);
		 * favoriteDao.deleteFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.NOT_EXISTS; }
		 */
		return decModelFavorite(commentId, userId, Favorite.FAVORITE_COMMENT);

	}

	@Override
	public int incrementQuestionFavorite(int questionId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * if (!feedDao.isFeedExistsById(questionId)) { return
		 * Status.NOT_EXISTS; } Favorite favorite = new Favorite(questionId,
		 * userId, Favorite.FAVORITE_QUESTION); if
		 * (!favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.incrementNum(Constant.FAVORITE_NUM, userId);
		 * feedDao.incrementNum(Constant.FAVORITE_NUM, questionId);
		 * favoriteDao.addFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.EXISTS; }
		 */
		return incModelFavorite(questionId, userId, Favorite.FAVORITE_QUESTION);

	}

	@Override
	public int decrementQuestionFavorite(int questionId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * if (!feedDao.isFeedExistsById(questionId)) { return
		 * Status.NOT_EXISTS; } Favorite favorite = new Favorite(questionId,
		 * userId, Favorite.FAVORITE_QUESTION); if
		 * (favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.decrementNum(Constant.FAVORITE_NUM, userId);
		 * feedDao.decrementNum(Constant.FAVORITE_NUM, questionId);
		 * favoriteDao.deleteFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.NOT_EXISTS; }
		 */
		return decModelFavorite(questionId, userId, Favorite.FAVORITE_QUESTION);

	}

	@Override
	public int incrementAnswerFavorite(int answerId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * Answer answer = answerDao.queryAnswerById(answerId); if (answer ==
		 * null) { return Status.NOT_EXISTS; } Favorite favorite = new
		 * Favorite(answerId, userId, Favorite.FAVORITE_ANSWER); if
		 * (!favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.incrementNum(Constant.FAVORITE_NUM, userId);
		 * answerDao.incrementNum(Constant.FAVORITE_NUM, answerId);
		 * favoriteDao.addFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.EXISTS; }
		 */
		return incModelFavorite(answerId, userId, Favorite.FAVORITE_ANSWER);

	}

	@Override
	public int decrementAnswerFavorite(int answerId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * Answer answer = answerDao.queryAnswerById(answerId); if (answer ==
		 * null) { return Status.NOT_EXISTS; } Favorite favorite = new
		 * Favorite(answerId, userId, Favorite.FAVORITE_ANSWER); if
		 * (favoriteDao.isFavoriteExists(favorite)) {
		 * userDao.decrementNum(Constant.FAVORITE_NUM, userId);
		 * answerDao.decrementNum(Constant.FAVORITE_NUM, answerId);
		 * favoriteDao.deleteFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.NOT_EXISTS; }
		 */
		return decModelFavorite(answerId, userId, Favorite.FAVORITE_ANSWER);
	}

	@Override
	public int incrementNoteFavorite(int noteId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * if (!feedDao.isFeedExistsById(noteId)) { return Status.NOT_EXISTS; }
		 * Favorite favorite = new Favorite(noteId, userId,
		 * Favorite.FAVORITE_NOTE); if (!favoriteDao.isFavoriteExists(favorite))
		 * { userDao.incrementNum(Constant.FAVORITE_NUM, userId);
		 * feedDao.incrementNum(Constant.FAVORITE_NUM, noteId);
		 * favoriteDao.addFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.EXISTS; }
		 */
		return incModelFavorite(noteId, userId, Favorite.FAVORITE_NOTE);
	}

	@Override
	public int decrementNoteFavorite(int noteId, int userId) {
		// TODO Auto-generated method stub
		/*
		 * if (!feedDao.isFeedExistsById(noteId)) { return Status.NOT_EXISTS; }
		 * Favorite favorite = new Favorite(noteId, userId,
		 * Favorite.FAVORITE_NOTE); if (favoriteDao.isFavoriteExists(favorite))
		 * { userDao.decrementNum(Constant.FAVORITE_NUM, userId);
		 * feedDao.decrementNum(Constant.FAVORITE_NUM, noteId);
		 * favoriteDao.deleteFavorite(favorite); return Status.SUCCESS; } else {
		 * return Status.NOT_EXISTS; }
		 */
		return decModelFavorite(noteId, userId, Favorite.FAVORITE_NOTE);
	}

	@Override
	public List<Favorite> getFavoriteListByUserId(int userId) {
		// TODO Auto-generated method stub
		return favoriteDao.queryFavoriteListByUserId(userId);
	}

	@Override
	public boolean isFavoriteExistsByKey(int userId, int objectId, int type) {
		// TODO Auto-generated method stub
		return favoriteDao.isFavoriteExistsByKey(userId, objectId, type);
	}

	// targetDao由type产生、在Controller层已经确定了type、应当不用switch语句了
	public int incModelFavorite(int targetId, int userId, int type) {
		System.out.println(targetDaos);
		// checkIsTargetExists
		NumOperationDao targetDao = targetDaos.get(type);
		System.out.println(targetDao);
		if (!targetDao.isModelExistsById(targetId)) {
			return Status.NOT_EXISTS;
		}
		Favorite favorite = new Favorite(targetId, userId, type);
		// checkIsFavoriteAlreadyExists
		if (!favoriteDao.isFavoriteExists(favorite)) {
			userDao.incrementNum(Constant.FAVORITE_NUM, userId);
			targetDao.incrementNum(Constant.FAVORITE_NUM, targetId);
			favoriteDao.addFavorite(favorite);
			return Status.SUCCESS;
		} else {
			return Status.EXISTS;
		}

	}

	public int decModelFavorite(int targetId, int userId, int type) {
		// TODO Auto-generated method stub
		// checkIsTargetExists
		NumOperationDao targetDao = targetDaos.get(type);
		if (!targetDao.isModelExistsById(targetId)) {
			return Status.NOT_EXISTS;
		}
		Favorite favorite = new Favorite(targetId, userId, type);
		// checkIsFavoriteNotExists
		if (favoriteDao.isFavoriteExists(favorite)) {
			userDao.decrementNum(Constant.FAVORITE_NUM, userId);
			targetDao.decrementNum(Constant.FAVORITE_NUM, targetId);
			favoriteDao.deleteFavorite(favorite);
			return Status.SUCCESS;
		} else {
			return Status.NOT_EXISTS;
		}
	}

}
