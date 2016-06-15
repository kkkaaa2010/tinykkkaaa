package com.tinykkkaaa.platform.core.service;

import com.tinykkkaaa.platform.core.SystemManager;
import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;
import com.tinykkkaaa.platform.core.session.Context;
import com.tinykkkaaa.platform.core.session.Session;

public class Service {
	
	public Session getSession() {
		return Context.getSession();
	}
	
	public DataPersistenceObject getDataPersistenceObject(){
		return Context.getDataPersistenceObject();
	}
	
	public SystemManager getSystemManager(){
		return SystemManager.getInstance();
	}

}
