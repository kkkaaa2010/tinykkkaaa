package com.tinykkkaaa.platform.core;

import com.tinykkkaaa.platform.core.cache.CacheManager;
import com.tinykkkaaa.platform.core.codetable.CodeTableManager;
import com.tinykkkaaa.platform.core.dao.DataPersistenceManager;

public class SystemManager {
	private static SystemManager instance = null;
	private SystemManager(){}
	
	public static SystemManager getInstance(){
		if(instance == null){
			instance = new SystemManager();
		}
		return instance;
	}
	
    private DataPersistenceManager dataPersistenceManager;
    private CacheManager cacheManager;
    private CodeTableManager codeTableManager;
	public DataPersistenceManager getDataPersistenceManager() {
		return dataPersistenceManager;
	}

	public void setDataPersistenceManager(
			DataPersistenceManager dataPersistenceManager) {
		this.dataPersistenceManager = dataPersistenceManager;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public CodeTableManager getCodeTableManager() {
		return codeTableManager;
	}

	public void setCodeTableManager(CodeTableManager codeTableManager) {
		this.codeTableManager = codeTableManager;
	}
}
