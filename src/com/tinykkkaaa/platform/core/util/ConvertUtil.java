package com.tinykkkaaa.platform.core.util;

public class ConvertUtil {
	public static String StrToStr(String source, int Bits) {
		if (source == null) {
			return "";
		}
		int j = source.length();
		if (j >= Bits) {
			return source.substring(0, Bits);
		}
		StringBuffer strtemp = new StringBuffer(Bits);

		for (int i = 1; i <= Bits - j; i++) {
			strtemp.append("0");
		}
		strtemp.append(source);
		return strtemp.toString();
	}
}
