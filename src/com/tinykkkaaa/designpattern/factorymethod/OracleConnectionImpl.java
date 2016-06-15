package com.tinykkkaaa.designpattern.factorymethod;

public class OracleConnectionImpl implements Connection {

	@Override
	public boolean prepareStatement(String sql) {
		System.out.println("do OracleConnectionImpl.prepareStatement(), sql: " + sql);
		return true;
	}

}
