package com.tinykkkaaa.designpattern.strategy.sale;

public class SuperSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice * 0.75;
	}

}
