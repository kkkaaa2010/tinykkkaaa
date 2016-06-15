package com.tinykkkaaa.platform.core.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;
import com.tinykkkaaa.platform.core.dao.DataSet;
import com.tinykkkaaa.platform.core.dao.DataSource;

public abstract class DataPersistenceObjectJDBCImpl implements DataPersistenceObject {
	
	private static final ThreadLocal<HashMap<String, HashMap<DataSource, Connection>>> threadLocal = new ThreadLocal<HashMap<String,HashMap<DataSource,Connection>>>();
	private DataSource dataSource;
	
	private static final String CONNECTIONMAP_THREADLOCAL_KEY = "CONNECTIONMAP";
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	private Connection getConnectionFromThread(){
		HashMap<String, HashMap<DataSource, Connection>> map = threadLocal.get();
		if(map == null){
			return null;
		}else{
			HashMap<DataSource, Connection> connectionMap = map.get(CONNECTIONMAP_THREADLOCAL_KEY);
			if(connectionMap == null){
				return null;
			}else{
				return connectionMap.get(this.dataSource);
			}
		}
	}
	
	private void setConnectionToThread(Connection connection){
		HashMap<String, HashMap<DataSource, Connection>> map = threadLocal.get();
		HashMap<DataSource, Connection> connectionMap = null;
		if(map == null){
			map = new HashMap<String, HashMap<DataSource, Connection>>();
			connectionMap = new HashMap<DataSource, Connection>();
		}else{
			connectionMap = map.get(CONNECTIONMAP_THREADLOCAL_KEY);
			if(connectionMap == null){
				connectionMap = new HashMap<DataSource, Connection>();
			}
		}
		connectionMap.put(this.dataSource, connection);
		map.put(CONNECTIONMAP_THREADLOCAL_KEY, connectionMap);
		threadLocal.set(map);
	}
	
	protected Connection getConnection(){
		Connection connection = this.getConnectionFromThread();
		try {
			if(connection == null || connection.isClosed()){
				connection = (Connection) this.dataSource.getConnection();
				this.setConnectionToThread(connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@Override
	public int beginTransition() {
		try {
			this.getConnection().setAutoCommit(false);
			return STATE_SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			return STATE_EXCEPTION;
		}
	};
	
	@Override
	public int commitTransition() {
        try {
            this.getConnection().commit();
            return STATE_SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return STATE_EXCEPTION;
        }
	};
	
	@Override
	public int rollbackTransition() {
        try {
            this.getConnection().rollback();
            return STATE_SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return STATE_EXCEPTION;
        }
	}
	
    @Override
    public void close() {
        try {
            this.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	@Override
	public DataSet executeQuery(String sql, Object[] parameters,
			int[] sqlTypes) {
		DataSet ds = new DataSet();
		PreparedStatement ps = null;
		try {
			ps = this.getConnection().prepareStatement(sql);
			
			if(parameters!=null && parameters.length>0){
				for(int i=0; i<parameters.length; i++){
					ps.setObject(i+1, sqlTypes==null ?  this.convertValue(parameters[i]): this.convertValue(parameters[i], sqlTypes[i]));
				}
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ResultSetMetaData md = rs.getMetaData();
				Map<String, Object> map =  new HashMap<String, Object>();
				int columnCount = md.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String key = md.getColumnName(i);
                    map.put(key.toUpperCase(), rs.getObject(i));
                }
                ds.addData(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ds;
	}

	@Override
	public DataSet executeQuery(String sql, Object[] parameters) {
		return this.executeQuery(sql, parameters, null);
	}

	@Override
	public DataSet executeQuery(String sql) {
		return this.executeQuery(sql, null, null);
	}
	
	@Override
	public int executeUpdate(String sql, Object[] parameters) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			ps = this.getConnection().prepareStatement(sql);
			
			if(parameters!=null && parameters.length>0){
				for(int i=0; i<parameters.length; i++){
					ps.setObject(i+1, this.convertValue(parameters[i]));
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	};
	
    protected Object convertValue(Object value) {
        Object result = value;
        if (value instanceof java.util.Date) {
            result = new java.sql.Date(((Date) value).getTime());
        }
        return result;
    }
    
    protected Object convertValue(Object value, int dataType) {
        Object result = value;
        if (result != null) {
            switch (dataType) {
                case Types.DATE:
                    if (value instanceof java.util.Date) {
                        result = new java.sql.Date(((Date) value).getTime());
                    }
                    break;
                case Types.TIMESTAMP:
                    if (value instanceof java.util.Date) {
                        result = new Timestamp(((java.util.Date) value).getTime());
                    }
                    break;
                case Types.TIME:
                    if (value instanceof java.util.Date) {
                        result = new Time(((java.util.Date) value).getTime());
                    }
                    break;
            }
        }
        return result;
    }
    
    @Override
    public abstract DataSet executeQueryFrom(String sql, Object[] parameters,
                                             int[] sqlTypes, int begin, int end);
}
