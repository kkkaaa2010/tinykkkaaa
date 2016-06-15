package com.tinykkkaaa.designpattern.proxy.staticstate;

public class Test {
	public static void main(String[] args) {
		UserServiceProxy proxy = new UserServiceProxy(new UserServiceImpl());
		proxy.saveUser();
	}
}
