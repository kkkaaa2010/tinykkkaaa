package com.tinykkkaaa.platform.core.dao;

public interface DataPersistenceObject {
	int STATE_FAILED = 0;
	int STATE_SUCCESS = 1;
	int STATE_EXCEPTION = -1;
	
	public void close();
	
	public int beginTransition();
	public int commitTransition();
	public int rollbackTransition();
	
	public void setDataSource(DataSource dataSource);
	public DataSource getDataSource();
	
	public DataSet executeQuery(String sql, Object[] parameters, int[] sqlTypes);
	public DataSet executeQuery(String sql, Object[] parameters);
	public DataSet executeQuery(String sql);
	
    public abstract DataSet executeQueryFrom(String sql, Object[] parameters,
                                             int[] sqlTypes, int begin, int end);
    
    public int executeUpdate(String sql, Object[] paramters);
}
