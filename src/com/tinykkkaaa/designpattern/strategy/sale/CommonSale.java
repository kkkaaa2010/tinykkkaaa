package com.tinykkkaaa.designpattern.strategy.sale;

public class CommonSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice;
	}

}
