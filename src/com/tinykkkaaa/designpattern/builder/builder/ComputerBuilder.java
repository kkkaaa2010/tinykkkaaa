package com.tinykkkaaa.designpattern.builder.builder;

import com.tinykkkaaa.designpattern.builder.Computer;

public abstract class ComputerBuilder {
	protected Computer computer;
	
	public void createComputer(String name){
		this.computer = new Computer(name);
	}
	public Computer getComputer(){
		return this.computer;
	}
	
	public void buildComputer(){
		buildMaster();
		buildDisplay();
		buildKeyboard();
		buildMouse();
	}
	
	protected abstract void buildMaster();
	protected abstract void buildDisplay();
	protected abstract void buildKeyboard();
	protected abstract void buildMouse();
}
