package com.tinykkkaaa.designpattern.decorator.drink;

public class Milk implements IDrink {
	
	private double money;
	private String drinkdesciption;
	
	public Milk(double money, String drinkdesciption){
		this.money = money;
		this.drinkdesciption = drinkdesciption;
	}

	@Override
	public double cost() {
		return this.money;
	}

	@Override
	public String drinkDesciption() {
		return this.drinkdesciption;
	}

}
