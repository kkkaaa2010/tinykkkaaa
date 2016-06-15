package com.tinykkkaaa.designpattern.strategy.sale;

public class GoldSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return  originalPrice * 0.5;
	}

}
