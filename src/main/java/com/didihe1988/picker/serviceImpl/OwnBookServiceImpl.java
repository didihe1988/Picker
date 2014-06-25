package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didihe1988.picker.mapper.OwnBookMapper;
import com.didihe1988.picker.service.OwnBookService;

@Service
public class OwnBookServiceImpl implements OwnBookService {
	@Autowired
	OwnBookMapper ownBookMapper;

	@Override
	public void addBook(int bookId, int userId) {
		// TODO Auto-generated method stub
		ownBookMapper.add(bookId, userId);
	}

	@Override
	public void deleteBook(int bookId, int userId) {
		// TODO Auto-generated method stub
		ownBookMapper.delete(bookId, userId);
	}

	@Override
	public List<Integer> findAllByUserId(int userId) {
		// TODO Auto-generated method stub
		return ownBookMapper.queryBookByUserId(userId);
	}

}
