package com.tinykkkaaa.designpattern.command;

public class Programmer {
	private String name;
	
	public Programmer(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void handleDemand(String demandName){
		System.out.println(this.name + "处理新增业务需求("+ demandName +")...");
	}
	public void handleBug(String bugName){
		System.out.println(this.name + "处理bug("+ bugName +")...");
	}
	public void handleProblem(String problemName){
		System.out.println(this.name + "处理问题("+ problemName +")...");
	}
}
