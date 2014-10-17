package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.AnswerDp;
import com.didihe1988.picker.model.json.AnswerJson;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.utils.DateUtils;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private FeedDao feedDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private RelatedImageDao relatedImageDao;

	@Override
	public int addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		int status = answerDao.addAnswer(answer);
		if (status == -1) {
			return Status.EXISTS;
		}

		/*
		 * Question answerNum++
		 */
		feedDao.incrementNum(Constant.ANSWER_NUM, answer.getQuestionId());
		/*
		 * User answerNum++
		 */
		userDao.incrementNum(Constant.ANSWER_NUM, answer.getReplierId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteAnswer(Answer answer, int userId) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		// 删除之前检验是否存在
		if (!answerDao.checkOperateValidation(userId, answer.getId())) {
			return Status.INVALID;
		}
		answerDao.deleteAnswer(answer);
		return Status.SUCCESS;
	}

	@Override
	public int deleteAnswerById(int id, int userId) {
		// TODO Auto-generated method stub
		return deleteAnswer(answerDao.queryAnswerById(id), userId);
	}

	@Override
	public int updateAnswer(Answer answer, int userId) {
		// TODO Auto-generated method stub
		if (answer == null) {
			return Status.NULLPOINTER;
		}
		if (!answerDao.checkOperateValidation(userId, answer.getId())) {
			return Status.INVALID;
		}
		int status = answerDao.updateAnswer(answer);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isAnswerExistsById(int id) {
		// TODO Auto-generated method stub
		return answerDao.isAnswerExistsById(id);
	}

	@Override
	public boolean isAnswerExistsByKey(int questionId, int replierId) {
		// TODO Auto-generated method stub
		return answerDao.isAnswerExsitsByKey(questionId, replierId);
	}

	@Override
	public boolean isAnswerOfUserExists(int userId) {
		// TODO Auto-generated method stub
		return answerDao.isAnswerOfUserAlreadyExists(userId);
	}

	@Override
	public Answer getAnswerById(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerById(id);
	}

	@Override
	public List<Answer> getAnswerListByQuestionId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerByQuestionId(id);
	}

	@Override
	public int getLatestAnswerIdByQuestionId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryLatestAnswerIdByQuestionId(id);
	}

	@Override
	public boolean checkOperateValidation(int userId, int answerId) {
		// TODO Auto-generated method stub
		return answerDao.checkOperateValidation(userId, answerId);
	}

	@Override
	public List<Answer> getAnswerListByReplierId(int id) {
		// TODO Auto-generated method stub
		return answerDao.queryAnswerByReplierId(id);
	}

	@Override
	public AnswerDp getAnswerDpByAnswerId(int id, int userId) {
		// TODO Auto-generated method stub
		Answer answer = answerDao.queryAnswerById(id);
		return getAnswerDpByAnswer(answer, userId);
	}

	@Override
	public List<AnswerDp> getAnswerDpListByQuestionId(int id, int userId) {
		// TODO Auto-generated method stub
		return getAnswerDpListFormAnswerList(getAnswerListByQuestionId(id),
				userId);
	}

	@Override
	public List<AnswerDp> getAnswerDpListByReplierId(int id, int userId) {
		// TODO Auto-generated method stub
		return getAnswerDpListFormAnswerList(getAnswerListByReplierId(id),
				userId);
	}

	private AnswerDp getAnswerDpByAnswer(Answer answer, int userId) {
		String questionName = feedDao.queryFeedById(answer.getQuestionId())
				.getTitle();
		User user = userDao.queryUserById(answer.getReplierId());
		return new AnswerDp(answer, questionName, user.getUsername(),
				user.getSignature(), user.getAvatarUrl(),
				favoriteDao.isFavoriteExistsByKey(userId, answer.getId(),
						Favorite.FAVORITE_ANSWER),
				getImageUrlsFromAnswer(answer));
	}

	private List<String> getImageUrlsFromAnswer(Answer answer) {
		return relatedImageDao.queryImageUrlsByKey(answer.getId(),
				RelatedImage.ANSWER_IMAGE);
	}

	private List<AnswerDp> getAnswerDpListFormAnswerList(
			List<Answer> answerList, int userId) {
		final List<AnswerDp> list = new ArrayList<AnswerDp>();
		for (Answer answer : answerList) {
			AnswerDp answerDp = getAnswerDpByAnswer(answer, userId);
			list.add(answerDp);
		}
		return list;
	}

	@Override
	public List<AnswerDp> search(String content, int userId) {
		// TODO Auto-generated method stub
		return getAnswerDpListFormAnswerList(answerDao.search(content), userId);
	}

	@Override
	public List<AnswerJson> getAnswerJsons(int userId, int page) {
		// TODO Auto-generated method stub
		List<Answer> answerList = answerDao
				.queryAnswerByReplierId(userId, page);
		List<AnswerJson> list = new ArrayList<AnswerJson>();
		for (Answer answer : answerList) {
			String imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					answer.getId(), RelatedImage.ANSWER_IMAGE);
			Feed feed = feedDao.queryFeedById(answer.getQuestionId());
			AnswerJson answerJson = new AnswerJson(feed.getTitle(), imageUrl,
					"/detail/" + answer.getQuestionId(),
					DateUtils.getDate(answer.getDate()), answer.getContent());
			list.add(answerJson);
		}
		return list;
	}

}
