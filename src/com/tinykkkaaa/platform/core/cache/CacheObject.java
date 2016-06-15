package com.tinykkkaaa.platform.core.cache;

import java.io.Serializable;
import java.util.Date;

public class CacheObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Object value;
	private Date expired;
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
}
