package com.tinykkkaaa.designpattern.factorymethod;

public class MysqlConnectionImpl implements Connection {

	@Override
	public boolean prepareStatement(String sql) {
		System.out.println("do MysqlConnectionImpl.prepareStatement(), sql: " + sql);
		return true;
	}

}
