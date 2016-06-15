package com.tinykkkaaa.designpattern.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tinykkkaaa.designpattern.command.task.ITask;

public class ProductManager {
	
	private static final int MAX_TASK_NUMBER = 5;
	
	private List<ITask> taskList;
	private List<Programmer> programmerList;
	
	private int currentIndex = 0;
	
	public ProductManager(Programmer...programmers){
		if(programmers==null || programmers.length==0){
			throw new RuntimeException("产品经理手下没有程序猿, 请重新分配");
		}
		this.taskList = new ArrayList<ITask>();
		this.programmerList = Arrays.asList(programmers);
	}
	
	public void receive(ITask task){
		this.taskList.add(task);
	}
	
	//将业务员提出的task分配给程序员
	public void assign(){
		if(this.taskList != null){
			for(int i=0; i<this.taskList.size() && i<MAX_TASK_NUMBER; i++){
				this.taskList.get(i).setProgrammer(chooseProgrammer());
				this.taskList.get(i).handle();
			}
			
			int tasklength = this.taskList.size();
			if(tasklength > MAX_TASK_NUMBER){
				ITask[] copyTask = new ITask[tasklength - MAX_TASK_NUMBER];
				System.arraycopy(taskList.toArray(), MAX_TASK_NUMBER, copyTask, 0, copyTask.length);
				this.taskList = Arrays.asList(copyTask);
			}else{
				this.taskList = null;
			}
		}else{
			System.out.println("列表中没有可以分配的任务!");
		}
	}
	
	public Programmer chooseProgrammer(){
		int length = this.programmerList.size();
		if(this.currentIndex == length){
			this.currentIndex = 0;
		}
		return this.programmerList.get(this.currentIndex++);
	}
	
	public void printTaskList(){
		System.out.println("-----------------打印任务列表(start)-----------------");
		if(this.taskList == null || this.taskList.size() == 0){
			System.out.println("-----列表中没有任务-----");
		}else{
			for(ITask task : this.taskList){
				System.out.println(task);
			}
		}
		System.out.println("-----------------打印任务列表(end)-----------------");
	}
	
}
