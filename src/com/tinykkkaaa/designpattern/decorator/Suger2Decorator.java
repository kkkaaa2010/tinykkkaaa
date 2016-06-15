package com.tinykkkaaa.designpattern.decorator;

import com.tinykkkaaa.designpattern.decorator.drink.IDrink;

public class Suger2Decorator extends SugerDecorator {
	
	public Suger2Decorator(IDrink drink) {
		super(drink);
	}
	
	@Override
	public void addMoney(double money) {
		super.addMoney(money);
		sendCard();
	}

	public void sendCard(){
		System.out.println("---送代金券---");
	}

}
