package com.tinykkkaaa.designpattern.command.task;

import com.tinykkkaaa.designpattern.command.Programmer;

public class DemandTaskImpl implements ITask {
	
	private Programmer programmer;
	private String name;
	
	public DemandTaskImpl(String name){
		this.name = name;
	}

	@Override
	public void handle() {
		this.programmer.handleDemand(this.name);
	}

	@Override
	public void setProgrammer(Programmer programmer) {
		this.programmer = programmer;
	}
	
	@Override
	public String toString() {
		return "需求任务名称: " + this.name;
	}
}
