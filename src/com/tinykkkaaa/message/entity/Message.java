package com.tinykkkaaa.message.entity;

import java.util.Date;

public class Message {
	private String messageid;
	private String messagetype;
	private String messagestatus;
	private String title;
	private String content;
	private String receiver;
	private String sender;
	private Date createtime;
	
	public Message(){}
	
	public Message(String messageid, String messagetype, String messagestatus,
			String title, String content, String receiver, String sender,
			Date createtime) {
		this.messageid = messageid;
		this.messagetype = messagetype;
		this.messagestatus = messagestatus;
		this.title = title;
		this.content = content;
		this.receiver = receiver;
		this.sender = sender;
		this.createtime = createtime;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getMessagestatus() {
		return messagestatus;
	}

	public void setMessagestatus(String messagestatus) {
		this.messagestatus = messagestatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
