package com.tinykkkaaa.designpattern.simplefactory;

public class Test {
	public static void main(String[] args) {
		IProduct producta = Factory.getProduct("a");
		IProduct productb = Factory.getProduct("b");
		producta.printProductXX();
		productb.printProductXX();
	}
}
