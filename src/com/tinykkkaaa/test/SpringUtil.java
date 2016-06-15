package com.tinykkkaaa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	
	public static ApplicationContext context;
	
	public static Object getBean(String beanName){
		if(context == null){
			//加载多个xml
//			context = new ClassPathXmlApplicationContext("file:" + System.getProperty("user.dir")+"/WebRoot/WEB-INF/applicationContext.xml",
//					"file:" + System.getProperty("user.dir")+"/WebRoot/WEB-INF/applicationContext-jms.xml");
			
			context = new ClassPathXmlApplicationContext("file:" + System.getProperty("user.dir")+"/WebRoot/WEB-INF/applicationContext-jms.xml");
		}
		return context.getBean(beanName);
	}
}
