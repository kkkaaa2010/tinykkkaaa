package com.tinykkkaaa.designpattern.singleton;

public class Singleton {
	private static Singleton instance;
	
	private Singleton(){}
	
	public static Singleton getInstace(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
}
