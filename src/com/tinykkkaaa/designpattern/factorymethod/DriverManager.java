package com.tinykkkaaa.designpattern.factorymethod;

import java.util.ArrayList;

public class DriverManager {
	private DriverManager(){}
	
	private static ArrayList<Driver> driverList = new ArrayList<Driver>();
	
	public static void registerDriver(Driver driver){
		driverList.add(driver);
	}
	
	public static Connection getConnection(String url){
		Connection connection = null;
		for(Driver driver : driverList){
			connection = driver.createConnection(url);
			if(connection == null){
				continue;
			}else{
				break;
			}
		}
		return connection;
	}
}
