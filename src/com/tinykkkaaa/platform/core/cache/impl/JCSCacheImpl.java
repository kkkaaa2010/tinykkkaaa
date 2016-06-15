package com.tinykkkaaa.platform.core.cache.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.control.CompositeCacheManager;

import com.tinykkkaaa.platform.core.cache.CacheObject;
import com.tinykkkaaa.platform.core.cache.ICache;

public class JCSCacheImpl implements ICache {
	
	public static JCS objCache;
	public static int subLength = 17;
	
	private CompositeCacheManager ccManager;
	private String cacheFilePath;
	
	public String getCacheFilePath() {
		return cacheFilePath;
	}
	public void setCacheFilePath(String cacheFilePath) {
		this.cacheFilePath = cacheFilePath;
	}

	private void init(){
		if(objCache == null){
			try {
				ccManager=CompositeCacheManager.getUnconfiguredInstance();
	            Properties props = new Properties();
	            try {
	            	String classPath = JCSCacheImpl.class.getResource("/").getPath();
	            	String fullPath = classPath.substring(0, classPath.length()-JCSCacheImpl.subLength) + cacheFilePath;
					props.load(new FileInputStream(new File(fullPath)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            ccManager.configure(props);
	            objCache = JCS.getInstance("objCache");
			} catch (CacheException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object get(String key, String catalog) {
		init();
		CacheObject cacheObject = (CacheObject) objCache.getFromGroup(key, catalog);
		if(cacheObject == null){
			return null;
		}
		Date expired = cacheObject.getExpired();
		if(expired != null && (System.currentTimeMillis()-expired.getTime())>0L){
			remove(key, catalog);
			return null;
		}
		return cacheObject.getValue();
	}

	@Override
	public void put(String key, Object value, String catalog, Date expired) {
		init();
		CacheObject cacheObject = new CacheObject();
		cacheObject.setExpired(new Date(expired.getTime()+System.currentTimeMillis()));
		cacheObject.setValue(value);
		try {
			objCache.putInGroup(key, catalog, cacheObject);
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(String key, String catalog) {
		objCache.remove(key, catalog);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeWithCatalog(String catalog) {
		init();
		Set<String> set = objCache.getGroupKeys(catalog);
		for(String key : set){
			objCache.remove(key, catalog);
		}
	}
}
