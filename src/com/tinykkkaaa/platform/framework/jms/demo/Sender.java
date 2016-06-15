package com.tinykkkaaa.platform.framework.jms.demo;

import java.util.Hashtable;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Sender {
    public void send(String msg,String user) throws Exception {
        String icf = "weblogic.jndi.WLInitialContextFactory";
        String url = "t3://localhost:7001";

        // Initialise JNDI properties
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, icf);
        env.put(Context.PROVIDER_URL, url);

        // Lookup
        InitialContext initContext = new InitialContext(env);
        
        //1, 获取连接工厂
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory)initContext.lookup("JMSConnectionFactory");
        //2，创建连接
        TopicConnection connection = connectionFactory.createTopicConnection();
        //3, 创建会话
        TopicSession session = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
        //4, 获取目的地
        Topic topic = (Topic)initContext.lookup("MyJMSTopic");
        //5, 创建生产者
        TopicPublisher publisher = session.createPublisher(topic);      
        initContext.close();
        
        // Send Message
        TextMessage message = session.createTextMessage(msg);
        message.setStringProperty("user",user);
        publisher.publish(message);
        System.out.println("Send:"+message);
        connection.close();
    }
}
