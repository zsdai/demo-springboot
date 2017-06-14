package com.demo.test;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MessageTest {
	@Autowired
	private Destination destination;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void test() {
		System.out.println("我发消息了！");
		this.jmsTemplate.convertAndSend(destination, "Hello ActiveMQ!");
	}
}
