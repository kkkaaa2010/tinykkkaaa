package com.tinykkkaaa.designpattern.abstractfactory.factory;

import com.tinykkkaaa.designpattern.abstractfactory.product.IProductA;
import com.tinykkkaaa.designpattern.abstractfactory.product.IProductB;
import com.tinykkkaaa.designpattern.abstractfactory.product.ProductAImpl;
import com.tinykkkaaa.designpattern.abstractfactory.product.ProductBImpl;

public class FactoryImpl implements IFactory {

	@Override
	public IProductA createIProductA() {
		return new ProductAImpl();
	}

	@Override
	public IProductB createIProductB() {
		return new ProductBImpl();
	}

}
