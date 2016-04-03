package com.tinykkkaaa.comm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @param formatterStr
	 * formatterStr="01" -> yyyyMMdd
	 * @return
	 */
	public static String getDate(String dateType){
		
		String formatterStr = "yyyy-MM-dd HH:mm:ss";
		
		if(dateType==null || dateType.trim().equals("")){
			formatterStr = "yyyyMMdd";
		}else if(dateType.equals("01")){
			formatterStr = "yyyyMMdd";
		}else{
			formatterStr = "yyyyMMdd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);
		return formatter.format(new Date());
	}
}
