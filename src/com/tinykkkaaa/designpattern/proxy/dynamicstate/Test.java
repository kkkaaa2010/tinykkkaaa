package com.tinykkkaaa.designpattern.proxy.dynamicstate;

public class Test {
	public static void main(String[] args) {
		DynamicProxy proxy = new DynamicProxy(new UserServiceImpl());
		IUserService service = (IUserService) proxy.getProxy();
		service.saveUser();
	}
}
