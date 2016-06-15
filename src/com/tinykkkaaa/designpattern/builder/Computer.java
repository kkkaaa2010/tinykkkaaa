package com.tinykkkaaa.designpattern.builder;

public class Computer {
	private String name;
	private String master;
	private String display;
	private String keyboard;
	private String mouse;
	
	public Computer(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}
	public String getMouse() {
		return mouse;
	}
	public void setMouse(String mouse) {
		this.mouse = mouse;
	}
	
	@Override
	public String toString() {
		return "笔记本信息: " + this.name + " -主机: " + this.master + " -显示器: " 
		       + this.display + " -键盘: " + this.keyboard + " -鼠标: " + this.mouse;
	}
}
