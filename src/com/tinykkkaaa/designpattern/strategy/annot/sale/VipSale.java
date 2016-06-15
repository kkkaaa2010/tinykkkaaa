package com.tinykkkaaa.designpattern.strategy.annot.sale;

@TotalValidRegion(min=1000, max=3000)
public class VipSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice * 0.8;
	}

}
