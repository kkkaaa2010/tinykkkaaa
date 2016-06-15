package com.tinykkkaaa.designpattern.simplefactory;

public class ProductAImpl implements IProduct {

	@Override
	public void printProductXX() {
		System.out.println("I am " + ProductAImpl.class.getName());
	}

}
