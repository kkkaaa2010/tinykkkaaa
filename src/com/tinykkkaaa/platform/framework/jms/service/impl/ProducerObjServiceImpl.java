package com.tinykkkaaa.platform.framework.jms.service.impl;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.tinykkkaaa.platform.framework.jms.service.ProducerService;

public class ProducerObjServiceImpl implements ProducerService {
	
	private static ProducerObjServiceImpl producer = null;
	private ProducerObjServiceImpl(){};
	
	public static ProducerService getInstance(){
		if(producer == null){
			producer = new ProducerObjServiceImpl();
		}
		return producer;
	}
	
	private JmsTemplate jmsTemplate;
	
	private Destination destination;
	private Destination responseQueue;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public Destination getResponseQueue() {
		return responseQueue;
	}
	public void setResponseQueue(Destination responseQueue) {
		this.responseQueue = responseQueue;
	}

	@Override
	public void sendMessage(Destination destination, final Object obj) {
		
		System.out.println("生产者ProducerObjServiceImpl正在发送了一条消息...");
		
		this.jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage om = session
						.createObjectMessage((Serializable) obj);
				om.setJMSReplyTo(responseQueue); //设置消息处理完成返回队列
				return om;
			}
		});
		
		//this.jmsTemplate.convertAndSend(this.destination, obj);
		System.out.println("生产者ProducerObjServiceImpl已经发送一条消息...");
	}

	/*
	 * 使用默认配置的destination
	 * (non-Javadoc)
	 * @see com.tinykkkaaa.platform.framework.jms.service.ProducerService#sendMessage(java.lang.Object)
	 */
	@Override
	public void sendMessage(final Object obj) {
		this.sendMessage(this.destination, obj);
	}
}
