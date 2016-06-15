package com.tinykkkaaa.designpattern.observer;

/**
 * 观察者模式
 * @author Administrator
观察者模式：
发布（release）--订阅（subscibe），变化（change）--更新（update）

注：eventlistener文件夹下为事件驱动模型
 */
public class Test {
	public static void main(String[] args) {
		Reader r1 = new Reader("张三");
		Reader r2 = new Reader("李四");
		Reader r3 = new Reader("王五");
		
		Writer w1 = new Writer("小六");
		Writer w2 = new Writer("小七");
		Writer w3 = new Writer("小八");
		
		r1.followWriter("小六");
		r1.followWriter("小七");
		r1.followWriter("小八");
		
		r2.followWriter("小八");
		
		r3.followWriter("小七");
		
		w1.addBook("故事会");
		w2.addBook("实战1");
		w2.addBook("实战2");
		w3.addBook("动物大全");
	}
}
