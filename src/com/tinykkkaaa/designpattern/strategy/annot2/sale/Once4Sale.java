package com.tinykkkaaa.designpattern.strategy.annot2.sale;

import com.tinykkkaaa.designpattern.strategy.annot2.annotation.OnceValidRegion;
import com.tinykkkaaa.designpattern.strategy.annot2.annotation.ValidRegion;

@OnceValidRegion(@ValidRegion(min=2000,order=40))
public class Once4Sale implements ISale {

	@Override
	public double sale(double originalPrice) {
		return originalPrice - 400;
	}

}
