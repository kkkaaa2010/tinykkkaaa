package com.tinykkkaaa.designpattern.observer.eventlistener;

/**
 * 
 * @author Administrator
事件驱动模型：
请求（request）--响应（response），事件发生（occur）--事件处理（handle） 
 */
public class Client {
	public static void main(String[] args) {
		ButtonJsp jsp = new ButtonJsp();
		jsp.getButton().click();
		jsp.getButton().dblClick();
		jsp.getButton().mouseMove("100", "120");
	}
}
