package com.tinykkkaaa.designpattern.strategy.sale;

public class VipSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice * 0.8;
	}

}
