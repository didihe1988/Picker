package com.didihe1988.picker.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;
import com.didihe1988.picker.model.PrivateMessage;
import com.didihe1988.picker.service.PrivateMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
public class PrivateMessageTest {
	@Autowired
	private PrivateMessageService privateMessageService;
	
	@Test
	public void test0() {
		PrivateMessage pMessage=new PrivateMessage(1, 2, "third time");
		privateMessageService.addPrivateMessage(pMessage);
	}
	
	@Test
	public void test1() {
		List<PrivateMessage> list=new ArrayList<PrivateMessage>();
		list=privateMessageService.getPrivateMessageByUserId(1);
		System.out.println(list.toString());
	}
}
