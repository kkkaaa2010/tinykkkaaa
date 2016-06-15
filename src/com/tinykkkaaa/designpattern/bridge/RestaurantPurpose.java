package com.tinykkkaaa.designpattern.bridge;

public class RestaurantPurpose implements IRegisterPurpose {

	@Override
	public void purpose() {
		System.out.println("用途: 饭店");
	}

}
