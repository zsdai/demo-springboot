package com.demo.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	// 接受消息方法
	@JmsListener(destination = "demo.queue")
	public void readMessage(String text) {
		System.out.println("接受到的消息是：" + text);
	}
}
