package com.tinykkkaaa.message.service.impl;

import com.tinykkkaaa.message.dao.MessageDao;
import com.tinykkkaaa.message.entity.Message;
import com.tinykkkaaa.message.service.MessageService;
import com.tinykkkaaa.platform.core.service.Service;

public class MessageServiceImpl extends Service implements MessageService {
	
	private MessageDao messageDao;

	public MessageServiceImpl(MessageDao messageDao){
		this.messageDao = messageDao;
	}

	@Override
	public String addMessage(Message message){
		return this.messageDao.addMessage(message);
	}

}
