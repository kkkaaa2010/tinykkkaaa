package com.tinykkkaaa.platform.core.util;

public class NumUtil {
	public static boolean isInt(String sInt) {
		try {
			new Integer(sInt);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}
}