package com.tinykkkaaa.designpattern.adapter;

public class BaseUserTarget {
	private String username;
	
	public BaseUserTarget(String username){
		this.username = username;
	}
	
	public void printUser(){
		System.out.println("用户信息, username = "+this.username);
	}
}
