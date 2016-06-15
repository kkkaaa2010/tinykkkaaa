package com.tinykkkaaa.platform.framework.jms.demo;

import java.io.PrintWriter;
import java.util.Hashtable;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class Receiver implements MessageListener {
    private boolean stop = false;
    PrintWriter out;
    HttpServletRequest m_request;
    HttpServletResponse m_response;
    TopicConnection connection;
    
    public void receive(PageContext pagecontext) {
        try {
            String icf = "weblogic.jndi.WLInitialContextFactory";
            String url = "t3://localhost:7001";
            //Initialise JNDI properties
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, icf);
            env.put(Context.PROVIDER_URL, url);
            
            m_request = (HttpServletRequest)pagecontext.getRequest();
            m_response = (HttpServletResponse)pagecontext.getResponse();
            out = m_response.getWriter();

            //Look up administratered objects
            InitialContext initContext = new InitialContext(env);
            
            //1, 获取连接工厂
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory)initContext.lookup("JMSConnectionFactory");
            //2，创建连接
            connection = connectionFactory.createTopicConnection();
            //3, 创建会话
            TopicSession session = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
            //4, 获取目的地
            Topic topic = (Topic)initContext.lookup("MyJMSTopic");
            //5, 创建消费者
            TopicSubscriber subscriber = session.createSubscriber(topic);
            initContext.close();
            
            subscriber.setMessageListener(this);
            connection.start();
            System.out.println("服务启动");
            out.println("服务启动");
            out.flush();
            //Wait for stop
            while (!stop) {
                Thread.sleep(1000);
            }
            connection.close();
            System.out.println("服务停止");
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    @Override
    public void onMessage(Message message) {
        try {
            String msgText = ((TextMessage) message).getText();
            if (message.getStringProperty("user").equalsIgnoreCase(m_request.getParameter("user"))) {
                out.println("<br>"+msgText);
                out.flush();
                System.out.println(msgText);
                if ("quit".equals(msgText)) {
                    stop = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop = true;
        }
    }
}
