package com.tinykkkaaa.platform.framework.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("监听器ConsumerMessageListener接收消息, 消息内容是：" + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}  
	}

}
