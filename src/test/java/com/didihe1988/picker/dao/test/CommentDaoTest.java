package com.didihe1988.picker.dao.test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.apache.catalina.valves.CometConnectionManagerValve;
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
public class CommentDaoTest {
	@Autowired	CommentDao commentDao;
	
	//testAddComment
	@Test
	public void test01()
	{
		Comment comment=new Comment();
		comment.setContent("miao");
		comment.setProducerId(1);
		comment.setReceiverId(3);
		int status=commentDao.addComment(comment);
		assertSame(1, status);
	}
	
	//testQueryCommentById
	@Test
	public void test02() 
	{
		Comment comment=commentDao.queryCommentById(1);
		assertNotNull(comment);
		System.out.println(comment.toString());
	}
	
	//testIsCommentExists
	@Test
	public void test03() 
	{
		Comment comment=commentDao.queryCommentById(1);
		boolean isCommentExists=commentDao.isCommentExists(comment);
		assertSame(true, isCommentExists);
	}
	
	//testIsCommentExists comment not exists
	@Test
	public void test04() 
	{
		Comment comment=commentDao.queryCommentById(20);
		boolean isCommentExists=commentDao.isCommentExists(comment);
		assertSame(false, isCommentExists);
	}
	
	//testUpdateComment
	@Test
	public void test05() 
	{
		Comment comment=commentDao.queryCommentById(4);
		comment.setContent("lalala");
		int status=commentDao.updateComment(comment);
		assertSame(1, status);
	}
	
	//testDeleteComment
	@Test
	public void test07() 
	{
		Comment comment=commentDao.queryCommentById(4);
		int status=commentDao.deleteComment(comment);
		assertSame(1, status);
	}
	
	
	
	
	
	
	
}
