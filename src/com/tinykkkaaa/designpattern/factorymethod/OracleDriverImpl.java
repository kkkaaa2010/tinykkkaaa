package com.tinykkkaaa.designpattern.factorymethod;

public class OracleDriverImpl implements Driver {
	
	static{
		DriverManager.registerDriver(new OracleDriverImpl());
	}

	@Override
	public Connection createConnection(String url) {
		if(url.indexOf("jdbc:oracle:thin") != -1){
			return new OracleConnectionImpl();
		}else{
			return null;
		}
	}

}
