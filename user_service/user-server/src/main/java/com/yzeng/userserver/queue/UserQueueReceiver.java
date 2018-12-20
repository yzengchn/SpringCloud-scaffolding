package com.yzeng.userserver.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "topic.message")
public class UserQueueReceiver {
	@RabbitHandler
	public void receiver(String userStr) {
		System.out.println("队列"+userStr);
	}
}
