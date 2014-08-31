package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.BoughtDao;
import com.didihe1988.picker.dao.MessageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Bought;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.service.BoughtService;

@Service
@Transactional
public class BoughtServiceImpl implements BoughtService {

	@Autowired
	private BoughtDao boughtDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Override
	public int addBought(Bought bought) {
		// TODO Auto-generated method stub
		if (bought == null) {
			return Status.NULLPOINTER;
		}
		int status = boughtDao.addBought(bought);
		if (status == -1) {
			return Status.EXISTS;
		}
		/*
		 * 还要增加图书的关注数 要不要检验一下status
		 */
		// bookDao.incrementFollowNum(bought.getBookId());

		/*
		 * User bookNum++
		 */
		userDao.incrementNum(Constant.BOOK_NUM, bought.getUserId());
		return Status.SUCCESS;
	}

	@Override
	public int updateBought(Bought bought) {
		// TODO Auto-generated method stub
		if (bought == null) {
			return Status.NULLPOINTER;
		}
		int status = boughtDao.updateBought(bought);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteBought(Bought bought) {
		// TODO Auto-generated method stub
		if (bought == null) {
			return Status.NULLPOINTER;
		}
		int status = boughtDao.deleteBought(bought);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Bought> getBoughtByBookId(int bookId) {
		// TODO Auto-generated method stub
		return boughtDao.queryBoughtByBookId(bookId);
	}

	@Override
	public List<Bought> getBoughtByUserId(int userId) {
		// TODO Auto-generated method stub
		return boughtDao.queryBoughtByUserId(userId);
	}

	@Override
	public boolean isBoughtExistsByKey(int userId, int bookId) {
		// TODO Auto-generated method stub
		return boughtDao.isBoughtExistsByKey(userId, bookId);
	}

	@Override
	public Bought getBoughtByUserIdAndBookId(int userId, int bookId) {
		// TODO Auto-generated method stub
		return boughtDao.queryBoughtByUserIdAndBookId(userId, bookId);
	}

}
