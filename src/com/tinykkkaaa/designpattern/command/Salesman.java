package com.tinykkkaaa.designpattern.command;

import com.tinykkkaaa.designpattern.command.task.ITask;

public class Salesman {
	private String name;
	private ProductManager pManager;
	
	public Salesman(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public ProductManager getpManager() {
		return pManager;
	}
	public void setpManager(ProductManager pManager) {
		this.pManager = pManager;
	}
	
	public void putDemand(ITask task){
		this.pManager.receive(task);
	}
	
	public void putBug(ITask task){
		this.pManager.receive(task);
	}
	
	public void putProblem(ITask task){
		this.pManager.receive(task);
	}
	
}
