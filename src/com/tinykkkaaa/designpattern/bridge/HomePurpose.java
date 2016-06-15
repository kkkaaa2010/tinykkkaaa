package com.tinykkkaaa.designpattern.bridge;

public class HomePurpose implements IRegisterPurpose {

	@Override
	public void purpose() {
		System.out.println("用途: 居住");
	}

}
