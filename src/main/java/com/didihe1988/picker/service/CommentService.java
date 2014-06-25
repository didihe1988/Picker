package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Comment;

public interface CommentService {
	public void addComment(Comment comment);
	public List<Comment> findAllByBookId(int bookId);
}
