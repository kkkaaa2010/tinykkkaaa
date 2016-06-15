package com.tinykkkaaa.designpattern.strategy.annot.sale;

@TotalValidRegion(min=3000, max=5000)
public class SuperSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice * 0.75;
	}

}
