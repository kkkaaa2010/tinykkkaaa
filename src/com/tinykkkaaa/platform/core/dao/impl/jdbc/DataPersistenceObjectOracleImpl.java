package com.tinykkkaaa.platform.core.dao.impl.jdbc;

import com.tinykkkaaa.platform.core.dao.DataSet;

public class DataPersistenceObjectOracleImpl extends
		DataPersistenceObjectJDBCImpl {
	
	public DataPersistenceObjectOracleImpl(){
		super();
	}

	@Override
	public DataSet executeQueryFrom(String sql, Object[] parameters,
			int[] sqlTypes, int begin, int end) {
		sql = "SELECT ROWNUM AS DATARN, ALLROWDATA.* FROM ( " + sql + " ) ALLROWDATA";
		sql = "SELECT * FROM ( " + sql + " ) WHERE DATARN BETWEEN "+ begin +" AND " + end;
		return this.executeQuery(sql, parameters, sqlTypes);
	}
}
