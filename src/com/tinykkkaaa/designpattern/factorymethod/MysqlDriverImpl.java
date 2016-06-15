package com.tinykkkaaa.designpattern.factorymethod;

public class MysqlDriverImpl implements Driver {
	
	static{
		DriverManager.registerDriver(new MysqlDriverImpl());
	}

	@Override
	public Connection createConnection(String url) {
		if(url.indexOf("jdbc:mysql:thin") != -1){
			return new MysqlConnectionImpl();
		}else{
			return null;
		}
	}

}
