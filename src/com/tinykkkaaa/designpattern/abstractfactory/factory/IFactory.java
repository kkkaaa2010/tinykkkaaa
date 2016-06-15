package com.tinykkkaaa.designpattern.abstractfactory.factory;

import com.tinykkkaaa.designpattern.abstractfactory.product.IProductA;
import com.tinykkkaaa.designpattern.abstractfactory.product.IProductB;

public interface IFactory {
	IProductA createIProductA();
	IProductB createIProductB();
}
