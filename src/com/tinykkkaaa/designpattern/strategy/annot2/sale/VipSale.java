package com.tinykkkaaa.designpattern.strategy.annot2.sale;

import com.tinykkkaaa.designpattern.strategy.annot2.annotation.TotalValidRegion;
import com.tinykkkaaa.designpattern.strategy.annot2.annotation.ValidRegion;

@TotalValidRegion(@ValidRegion(min=1000, max=3000,order=99))
public class VipSale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice * 0.8;
	}

}
