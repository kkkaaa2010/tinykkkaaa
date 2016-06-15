package com.tinykkkaaa.designpattern.command.task;

import com.tinykkkaaa.designpattern.command.Programmer;

public class ProblemTaskImpl implements ITask {
	
	private Programmer programmer;
	private String name;
	
	public ProblemTaskImpl(String name){
		this.name = name;
	}
	
	@Override
	public void handle() {
		this.programmer.handleProblem(this.name);
	}

	@Override
	public void setProgrammer(Programmer programmer) {
		this.programmer = programmer;
	}
	
	@Override
	public String toString() {
		return "问题任务名称: " + this.name;
	}
}
