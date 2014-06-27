package com.didihe1988.picker.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.didihe1988.picker.model.Comment;
@Repository
public class CommentMapper {
	public void insertComment(Comment comment) {
		System.out.println(comment.toString());
	}

	public List<Comment> queryCommentByBookId(int bookId) {
		List<Comment> commentList = new ArrayList<Comment>();
		for (int i = 0; i < 3; i++) {
			Comment comment = new Comment(i, i, i + 1, "content" + i);
			commentList.add(comment);
		}
		return commentList;
	}
}
