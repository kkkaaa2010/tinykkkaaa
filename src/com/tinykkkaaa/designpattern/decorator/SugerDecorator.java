package com.tinykkkaaa.designpattern.decorator;

import com.tinykkkaaa.designpattern.decorator.drink.IDrink;

public class SugerDecorator extends AbstractDecorator {
	
	private double extraMoney;

	public SugerDecorator(IDrink drink) {
		super(drink);
	}
	
	@Override
	public double cost() {
		return super.cost() + this.extraMoney;
	}
	
	@Override
	public String drinkDesciption() {
		String tmp = "";
		if(this.extraMoney > 0){
			tmp = "(加糖)";
		}
		return super.drinkDesciption() + tmp;
	}

	@Override
	public void addMoney(double money) {
		this.extraMoney = money;
	}

}
