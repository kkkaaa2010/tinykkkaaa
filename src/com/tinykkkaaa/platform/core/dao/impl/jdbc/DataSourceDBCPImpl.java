package com.tinykkkaaa.platform.core.dao.impl.jdbc;

import java.sql.SQLException;

import com.tinykkkaaa.platform.core.dao.DataSource;

public class DataSourceDBCPImpl extends DataSource {

	private javax.sql.DataSource dataSource;
	
	public javax.sql.DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Object getConnection() throws Exception {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
