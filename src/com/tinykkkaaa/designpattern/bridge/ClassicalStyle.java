package com.tinykkkaaa.designpattern.bridge;

public class ClassicalStyle implements IHouseStyle {

	@Override
	public void fix() {
		System.out.println("装修风格: 古典");
	}

}
