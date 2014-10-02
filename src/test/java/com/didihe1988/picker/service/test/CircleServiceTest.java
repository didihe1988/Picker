package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.service.CircleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CircleServiceTest {
	@Autowired
	private CircleService circleService;

	/*
	 * test addCircle
	 */
	@Test
	public void test01() {
		Circle circle = new Circle("lol", 1, "lalala");
		int status = circleService.addCircle(circle);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test02() {
		List<Circle> circles = circleService.getCircleListByEstablisherId(1);
		System.out.println(circles);
	}
	
	@Test
	public void test03() {
		List<Circle> circles = circleService.search("a");
		System.out.println(circles);
	}

}
