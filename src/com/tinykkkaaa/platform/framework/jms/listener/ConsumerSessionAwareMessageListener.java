package com.tinykkkaaa.platform.framework.jms.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class ConsumerSessionAwareMessageListener implements
		SessionAwareMessageListener<TextMessage> {
	
	private Destination destination;
	
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		TextMessage textMessage = (TextMessage) message;
		System.out.println("监听器ConsumerSessionAwareMessageListener接收消息...------------------------------");
		System.out.println("消息内容是：" + textMessage.getText());
		
		MessageProducer messageProducer = session.createProducer(destination);
		Message ttmessage = session.createTextMessage("监听器ConsumerSessionAwareMessageListener收到消息了...------------------------------");
		messageProducer.send(ttmessage);
		
	}

}
