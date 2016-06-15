package com.tinykkkaaa.designpattern.adapter.defaultadapter;

public class DeafPerson extends DefaultPersonAdapter {
	@Override
	public void write() {
		System.out.println("DeafPerson write...");
	}
}
