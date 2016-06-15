package com.tinykkkaaa.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取当前日期字符串
	 * @param dateType
	 * dateType="01" -> yyyyMMdd
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
	
	/*
	 * 字符串yyyy-MM-dd转yyyyMMdd
	 */
	public static String strConvertStr(String sourceStr){
		String convertStr = "";
		try {
			String formatterStr = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);
			Date date = formatter.parse(sourceStr);
			formatterStr = "yyyyMMdd";
			formatter = new SimpleDateFormat(formatterStr);
			convertStr = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertStr;
	}
}
