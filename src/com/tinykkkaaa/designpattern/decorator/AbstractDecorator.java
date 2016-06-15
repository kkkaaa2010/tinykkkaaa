package com.tinykkkaaa.designpattern.decorator;

import com.tinykkkaaa.designpattern.decorator.drink.IDrink;

public abstract class AbstractDecorator implements IDrink {
	
	private IDrink drink;
	
	public AbstractDecorator(IDrink drink){
		this.drink = drink;
	}

	@Override
	public double cost() {
		return this.drink.cost();
	}

	@Override
	public String drinkDesciption() {
		return this.drink.drinkDesciption();
	}
	
	public abstract void addMoney(double money);

}
