package com.tinykkkaaa.platform.core.dao;

public abstract class DataSource {
	private String sourceName;
	private String sourceType;
	private DataPersistenceObject dpo;
	
	public abstract Object getConnection() throws Exception;
	
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public DataPersistenceObject getDpo() {
		return dpo;
	}
	public void setDpo(DataPersistenceObject dpo) {
		this.dpo = dpo;
	}
}
