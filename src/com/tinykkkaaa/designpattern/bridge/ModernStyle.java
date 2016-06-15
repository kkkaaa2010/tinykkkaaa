package com.tinykkkaaa.designpattern.bridge;

public class ModernStyle implements IHouseStyle {

	@Override
	public void fix() {
		System.out.println("装修风格: 现代");
	}

}
