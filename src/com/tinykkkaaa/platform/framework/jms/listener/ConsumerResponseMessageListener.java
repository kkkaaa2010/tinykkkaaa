package com.tinykkkaaa.platform.framework.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.tinykkkaaa.message.dao.MessageDao;
import com.tinykkkaaa.message.service.MessageService;
import com.tinykkkaaa.platform.core.service.ServiceFactory;

public class ConsumerResponseMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		String result = "";
		
		try {
			if(message instanceof TextMessage){
				result =  ((TextMessage)message).getText();
			}
			if(message instanceof ObjectMessage){
				result = ((ObjectMessage)message).getObject().toString();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println("监听器ConsumerResponseMessageListener接收返回信息： " + result);
		
		//处理完成返回, 增加一条消息记录
		//type+"#"+status+"#"+title+"#"+content+"#"+receiver+"#"+sender;
		String[] arrayMsg = result.split("#");
		com.tinykkkaaa.message.entity.Message msg = new com.tinykkkaaa.message.entity.Message();
		msg.setMessagetype(arrayMsg[0]);
		msg.setMessagestatus(arrayMsg[1]);
		msg.setTitle(arrayMsg[2]);
		msg.setContent(arrayMsg[3]);
		msg.setReceiver(arrayMsg[4]);
		msg.setSender(arrayMsg[5]);
		
//		MessageService service = ServiceFactory.getInstance()
//		.getService("MessageService", MessageService.class, "MessageDao", MessageDao.class);
		
		MessageService service = ServiceFactory.getInstance().getService(MessageService.class, MessageDao.class);
		
		service.addMessage(msg);
	}

}
