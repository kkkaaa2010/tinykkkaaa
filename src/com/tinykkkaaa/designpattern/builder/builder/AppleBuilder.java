package com.tinykkkaaa.designpattern.builder.builder;

public class AppleBuilder extends ComputerBuilder {

	@Override
	protected void buildMaster() {
		this.computer.setMaster("apple主机");
	}

	@Override
	protected void buildDisplay() {
		this.computer.setDisplay("apple高清显示器");
	}

	@Override
	protected void buildKeyboard() {
		this.computer.setKeyboard("apple键盘");
	}

	@Override
	protected void buildMouse() {
		this.computer.setMouse("apple难用的鼠标");
	}

}
