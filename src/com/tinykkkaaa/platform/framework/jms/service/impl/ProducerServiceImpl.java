package com.tinykkkaaa.platform.framework.jms.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.tinykkkaaa.platform.framework.jms.service.ProducerService;

public class ProducerServiceImpl implements ProducerService {
	
	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendMessage(Destination destination, final Object message) {
		System.out.println("生产者ProducerServiceImpl发送了一个消息："
				+message+"------------------------------");
		this.jmsTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.toString());
			}
		});
	}
	@Override
	public void sendMessage(Object obj) {
		
	}

}
