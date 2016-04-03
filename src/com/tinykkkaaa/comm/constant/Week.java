package com.tinykkkaaa.comm.constant;

public enum Week {
	Sun("星期日",0), Mon("星期一",1), Thu("星期二",2), 
	Wed("星期三",3), Tue("星期四",4), Fri("星期五",5),
	Sat("星期六",6),;
	
	private String name;
	private int value;
	Week(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getValue(){
		return this.value;
	}
}
