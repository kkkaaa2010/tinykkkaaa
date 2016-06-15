package com.tinykkkaaa.designpattern.bridge;

public class House extends AbstractHouse {

	public House(IHouseStyle houseStyle, IRegisterPurpose registerPurpose) {
		super(houseStyle, registerPurpose);
	}

	@Override
	public void getHouseInfo() {
		System.out.println("打印房屋信息: ");
		getPurpose().purpose();
		getStyle().fix();
		
	}

}
