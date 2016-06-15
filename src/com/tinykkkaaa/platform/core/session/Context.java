package com.tinykkkaaa.platform.core.session;

import java.util.HashMap;
import java.util.Map;

import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;

public class Context {
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String,Object>>();
	public static void init(Session session, DataPersistenceObject dpo){
		Map<String, Object> map = threadLocal.get();
		if(map == null){
			map = new HashMap<String, Object>();
		}
		map.put("SESSION", session);
		map.put("DPO", dpo);
		threadLocal.set(map);
	}
	
	public static DataPersistenceObject getDataPersistenceObject(){
		Map<String, Object> map = threadLocal.get();
		return (DataPersistenceObject) map.get("DPO");
	}
	
	public static Session getSession(){
		Map<String, Object> map = threadLocal.get();
		return (Session) map.get("SESSION");
	}
}
