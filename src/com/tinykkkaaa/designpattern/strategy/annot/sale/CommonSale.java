package com.tinykkkaaa.designpattern.strategy.annot.sale;

@TotalValidRegion(max=1000)
public class CommonSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice;
	}

}
