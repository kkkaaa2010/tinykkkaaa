package com.tinykkkaaa.designpattern.simplefactory;

public class ProductBImpl implements IProduct {

	@Override
	public void printProductXX() {
		System.out.println("I am " + ProductBImpl.class.getName());
	}

}
