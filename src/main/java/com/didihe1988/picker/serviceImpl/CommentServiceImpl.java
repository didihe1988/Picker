package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didihe1988.picker.mapper.CommentMapper;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.insertComment(comment);
	}

	@Override
	public List<Comment> findAllByBookId(int bookId) {
		// TODO Auto-generated method stub
		return commentMapper.queryCommentByBookId(bookId);
	}
	
	
}
