package com.tinykkkaaa.designpattern.builder.builder;

public class HpBuilder extends ComputerBuilder {

	@Override
	protected void buildMaster() {
		this.computer.setMaster("惠普主机");
	}

	@Override
	protected void buildDisplay() {
		this.computer.setDisplay("惠普1024显示器");
	}

	@Override
	protected void buildKeyboard() {
		this.computer.setKeyboard("惠普键盘");
	}

	@Override
	protected void buildMouse() {
		this.computer.setMouse("惠普鼠标");
	}

}
