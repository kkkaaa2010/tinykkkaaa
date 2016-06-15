package com.tinykkkaaa.message.dao.impl;

import com.tinykkkaaa.message.dao.MessageDao;
import com.tinykkkaaa.message.entity.Message;
import com.tinykkkaaa.platform.core.console.util.DBAccessHelper;
import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;

public class MessageDaoImpl implements MessageDao {
	
	private DataPersistenceObject dpo;
	
	public MessageDaoImpl(DataPersistenceObject dpo){
		this.dpo = dpo;
	}

	@Override
	public String addMessage(Message message){
		String result = "00";
		
		String sql = "INSERT INTO T_PT_MESSAGE( MESSAGE_ID, MESSAGE_TYPE, MESSAGE_STATUS, TITLE, CONTENT, RECEIVER, SENDER, CREATETIME)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		Object[] objs = new Object[7];
		objs[0] = DBAccessHelper.getNextKey("T_PT_MESSAGE", 10);
		objs[1] = message.getMessagetype();
		objs[2] = message.getMessagestatus();
		objs[3] = message.getTitle();
		objs[4] = message.getContent();
		objs[5] = message.getReceiver();
		objs[6] = message.getSender();
		int i = this.dpo.executeUpdate(sql, objs);
		
		if(i > 0){
			result = "01";
		}
		return result;
	}

}
