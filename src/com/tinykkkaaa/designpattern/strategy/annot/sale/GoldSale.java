package com.tinykkkaaa.designpattern.strategy.annot.sale;

@TotalValidRegion(min=5000)
public class GoldSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return  originalPrice * 0.5;
	}

}
