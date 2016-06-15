package com.tinykkkaaa.platform.framework.jms.service;

import javax.jms.Destination;

public interface ProducerService {
	public void sendMessage(Destination destination, final Object obj);
	
	public void sendMessage(final Object obj);
}
