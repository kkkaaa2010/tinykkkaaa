package com.tinykkkaaa.designpattern.command.task;

import com.tinykkkaaa.designpattern.command.Programmer;

public class BugTaskImpl implements ITask {
	
	private Programmer programmer;
	private String name;
	
	public BugTaskImpl(String name){
		this.name = name;
	}

	@Override
	public void handle() {
		this.programmer.handleBug(this.name);
	}

	@Override
	public void setProgrammer(Programmer programmer) {
		this.programmer = programmer;
	}
	
	@Override
	public String toString() {
		return "Bug任务名称: " + this.name;
	}
}
