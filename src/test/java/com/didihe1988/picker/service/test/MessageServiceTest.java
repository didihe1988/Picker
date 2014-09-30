package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
public class MessageServiceTest {
	@Autowired
	private MessageService messageService;
	
	@Test
	public void test0() {
		List<Message> messageList=messageService.getMessageByReceiverIdAndFilter(1,Filter.MESSAGE_FOOTPRINT);
		System.out.println(messageList);
	}
	
	@Test
	public void test1() {
		List<Message> messageList=messageService.getMessageByReceiverIdAndFilter(2,Filter.MESSAGE_DYNAMIC);
		System.out.println(messageList);
	}
	
	@Test
	public void test2() {
		List<Message> messageList=messageService.getMessageByReceiverIdAndFilter(1,Filter.MESSAGE_RELATED);
		System.out.println(messageList);
	}
	
}
