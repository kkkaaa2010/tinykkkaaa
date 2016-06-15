package com.tinykkkaaa.platform.core.cache.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.tinykkkaaa.platform.core.cache.CacheObject;
import com.tinykkkaaa.platform.core.cache.ICache;

public class ConcurrentHMCacheImpl implements ICache {
	
	private static ConcurrentHashMap<String, CacheObject> data = new ConcurrentHashMap<String, CacheObject>();

	@Override
	public Object get(String key, String catalog) {
		CacheObject cacheObject = data.get(catalog+key);
		if(cacheObject == null){
			return null;
		}
		Date expired = cacheObject.getExpired();
		if(expired!=null && (System.currentTimeMillis()-expired.getTime())>0L){
			remove(key, catalog);
			return null;
		}
		return cacheObject.getValue();
	}

	@Override
	public void put(String key, Object value, String catalog, Date expired) {
		CacheObject cacheObject = new CacheObject();
		cacheObject.setExpired(new Date(expired.getTime()+System.currentTimeMillis()));
		cacheObject.setValue(value);
		data.put(catalog+key, cacheObject);
	}

	@Override
	public void remove(String key, String catalog) {
		data.remove(catalog+key);
	}

	@Override
	public void removeWithCatalog(String catalog) {
		Iterator<String> it = data.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.indexOf(catalog) == 0){
				data.remove(key);
			}
		}
	}
}
