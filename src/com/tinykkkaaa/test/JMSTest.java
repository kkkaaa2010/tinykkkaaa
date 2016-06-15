package com.tinykkkaaa.test;

import javax.jms.Destination;

import com.tinykkkaaa.platform.framework.jms.service.impl.ProducerObjServiceImpl;
import com.tinykkkaaa.platform.framework.jms.vo.Email;

public class JMSTest {
	public static void main(String[] args) {
		
//		ProducerServiceImpl service = (ProducerServiceImpl)SpringUtil.getBean("producerService");
//		Destination destination = (Destination) SpringUtil.getBean("queueDestination");
//		service.sendMessage(destination, "Hi, 今天是2016-04-11, 星期一");
		
//		ProducerServiceImpl service = (ProducerServiceImpl)SpringUtil.getBean("producerService");
//		Destination destination = (Destination) SpringUtil.getBean("sessionAwareQueue");
//		service.sendMessage(destination, "Hi, 今天是2016-04-11, 星期一(sessionAwareQueue)");
		
		ProducerObjServiceImpl service = (ProducerObjServiceImpl)SpringUtil.getBean("producerObjService");
		Destination destination = (Destination) SpringUtil.getBean("adapterQueue");
		Email email = new Email("luliang", "报销2015", "退票XXX");
		service.sendMessage(destination, email);
		
		email = new Email("6733@qq.com", "测试", "XXX");
		service.sendMessage(destination, email);
	}
}
