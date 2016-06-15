package com.tinykkkaaa.designpattern.builder;

import com.tinykkkaaa.designpattern.builder.builder.ComputerBuilder;

public class ComputerShop {
	private ComputerBuilder builder;
	
	public void setBuilder(ComputerBuilder builder){
		this.builder = builder;
	}
	
	//提货
	public Computer pickupComputer(){
		return	this.builder.getComputer();
	}
	
	public void constructComputer(String computerName){
		this.builder.createComputer(computerName);
		this.builder.buildComputer();
	}
}
