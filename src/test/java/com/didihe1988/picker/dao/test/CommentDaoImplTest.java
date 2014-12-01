package com.didihe1988.picker.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.model.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentDaoImplTest {
	@Autowired
	private CommentDao commentDao;

	@Test
	public void test01() {
		Comment comment = new Comment(1, 2, "lalala", Comment.COMMENT_ANSWER);
		commentDao.addModel(comment);
	}

}
