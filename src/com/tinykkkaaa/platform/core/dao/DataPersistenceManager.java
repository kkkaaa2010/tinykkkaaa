package com.tinykkkaaa.platform.core.dao;

import java.util.Map;

public class DataPersistenceManager {
	private static DataPersistenceManager instance = null;
	private Map<String, DataSource> dataSources;
	private Map<String, String> dataPersistenceObjectNames;
	private String defaultDataSource;
	
	private DataPersistenceManager(){}
	
	public static DataPersistenceManager getInstance(){
		if(instance == null){
			instance = new DataPersistenceManager();
		}
		return instance;
	}
	
	public DataPersistenceObject getDataPersistenceObject(String sourceName){
		if(sourceName == null || sourceName.length()==0){
			return this.getDefaultDataPersistenceObject();
		}
		DataPersistenceObject dpo = null;
		DataSource ds = null;
		try {
			ds = this.dataSources.get(sourceName);
			dpo = ds.getDpo();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		dpo.setDataSource(ds);
		
		return dpo;
	}
	
	public DataPersistenceObject getDefaultDataPersistenceObject(){
		return this.getDataPersistenceObject(this.defaultDataSource);
	}
	

	public Map<String, DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSources(Map<String, DataSource> dataSources) {
		this.dataSources = dataSources;
	}

	public Map<String, String> getDataPersistenceObjectNames() {
		return dataPersistenceObjectNames;
	}

	public void setDataPersistenceObjectNames(
			Map<String, String> dataPersistenceObjectNames) {
		this.dataPersistenceObjectNames = dataPersistenceObjectNames;
	}

	public String getDefaultDataSource() {
		return defaultDataSource;
	}

	public void setDefaultDataSource(String defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}
}