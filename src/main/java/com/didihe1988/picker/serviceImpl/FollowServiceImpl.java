package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.QuestionDao;
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
	private QuestionDao questionDao;

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
		} else {
			// Follow.FOLLOW_QUESTION
			questionDao.incrementNum(Constant.FOLLOW_NUM, follow.getSourceId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteFollow(Follow follow) {
		// TODO Auto-generated method stub
		if (follow == null) {
			return Status.NULLPOINTER;
		}
		int status = followDao.deleteFollow(follow);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}

		/*
		 * followNum-- 感觉应该多些检测以防减到负值
		 */
		if (follow.getSourceType() == Follow.FOLLOW_USER) {
			userDao.decrementNum(Constant.FOLLOW_NUM, follow.getSourceId());
		} else {
			// Follow.FOLLOW_QUESTION
			questionDao.decrementNum(Constant.FOLLOW_NUM, follow.getSourceId());
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
	public boolean isFollowExists(Follow follow) {
		// TODO Auto-generated method stub
		if (follow == null) {
			return false;
		}
		return followDao.isFollowExists(follow);
	}

	@Override
	public List<Follow> getFollowByFollowerId(int followerId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowByFollowerId(followerId);
	}

	@Override
	public List<Follow> getFollowByFollowedUserId(int followedUserId) {
		// TODO Auto-generated method stub
		return followDao.queryFollowByFollowedUserId(followedUserId);
	}

	@Override
	public List<Follow> getAllFollowForTest() {
		// TODO Auto-generated method stub
		return followDao.queryAllForTest();
	}

}
