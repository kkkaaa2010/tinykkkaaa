package com.tinykkkaaa.designpattern.proxy.staticstate;

public class UserServiceImpl implements IUserService {

	@Override
	public void saveUser() {
		System.out.println("---UserServiceImpl.saveUser()---");
	}

}
