package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.service.FollowService;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowDao followDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FeedDao feedDao;

	@Override
	public Follow getFollowById(int id) {
		// TODO Auto-generated method stub
		return followDao.queryFollowById(id);
	}

	@Override
	public int addFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (follow == null) {
			return Status.NULLPOINTER;
		}
		int status = followDao.addFollow(follow);
		if (status == -1) {
			return Status.EXISTS;
		}

		// followNum++
		if (follow.getSourceType() == Follow.FOLLOW_USER) {
			userDao.incrementNum(Constant.FOLLOW_NUM, follow.getSourceId());
			userDao.incrementNum(Constant.FOLLOW_OTHERSNUM,
					follow.getFollowerId());
		} else {
			// Follow.FOLLOW_QUESTION
			feedDao.incrementNum(Constant.FOLLOW_NUM, follow.getSourceId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteFollow(int sourceType, int followerId, int sourceId) {
		// TODO Auto-generated method stub
		/*
		 * check validation
		 */
		int status = followDao.deleteFollow(sourceType, followerId, sourceId);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}

		/*
		 * followNum-- 感觉应该多些检测以防减到负值
		 */
		if (sourceType == Follow.FOLLOW_USER) {
			userDao.decrementNum(Constant.FOLLOW_NUM, sourceId);
		} else {
			// Follow.FOLLOW_QUESTION
			feedDao.decrementNum(Constant.FOLLOW_NUM, sourceId);
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (follow == null) {
			return Status.NULLPOINTER;
		}
		int status = followDao.updateFollow(follow);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isFollowExistsById(int id) {
		// TODO Auto-generated method stub
		return followDao.isFollowExistsById(id);
	}

	@Override
	public boolean isFollowExistsByKey(int sourceType, int followerId,
			int sourceId) {
		// TODO Auto-generated method stub
		return followDao.isFollowExistsByKey(sourceType, followerId, sourceId);
	}

	/**
	 * @description 用户可以查看自己关注的所有内容
	 */
	@Override
	public List<Follow> getFollowListByFollowerId(int followerId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowListByFollowerId(followerId);
	}

	/**
	 * @description 用户可以查看自己关注的所有问题
	 */
	@Override
	public List<Follow> getFollowListByFollowerIdByQuestion(int followerId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowListByFollowerIdByType(followerId,
				Follow.FOLLOW_QUESTION);
	}

	/**
	 * @description 用户可以查看自己关注的所有用户
	 */
	@Override
	public List<Follow> getFollowListByFollowerIdByUser(int followerId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowListByFollowerIdByType(followerId,
				Follow.FOLLOW_USER);
	}

	/**
	 * @description 该用户被那些人关注
	 */
	@Override
	public List<Follow> getFollowListByFollowedUserId(int followedUserId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowListByFollowedUserId(followedUserId);
	}

	/**
	 * @description 该问题被那些人关注
	 */
	@Override
	public List<Follow> getFollowListByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowListByQuestionId(questionId);
	}

	@Override
	public List<Follow> getAllFollowForTest() {
		// TODO Auto-generated method stub
		return followDao.queryAllForTest();
	}

}
