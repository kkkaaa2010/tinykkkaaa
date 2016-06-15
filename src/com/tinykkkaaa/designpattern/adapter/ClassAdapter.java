package com.tinykkkaaa.designpattern.adapter;

public class ClassAdapter extends BaseUserTarget {

	public ClassAdapter(String username) {
		super(username);
	}
	
	public void printChangeUser(String username){
		System.out.println("用户信息, 改变username = "+username);
	}
}
