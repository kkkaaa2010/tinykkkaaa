package com.tinykkkaaa.test;

import com.tinykkkaaa.platform.core.console.util.DBAccessHelper;

public class PrimaryKeyTest {
	public static void main(String[] args) {
		System.out.println(DBAccessHelper.getNextKey("TEST", 10));
		System.exit(0);
	}
}
