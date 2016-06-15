package com.tinykkkaaa.designpattern.singleton;

public class BadSynchronizedSingleton {
	private static BadSynchronizedSingleton instance;
	
	private BadSynchronizedSingleton(){}
	
	public synchronized static BadSynchronizedSingleton getInstace(){
		if(instance == null){
			instance = new BadSynchronizedSingleton();
		}
		return instance;
	}
}
