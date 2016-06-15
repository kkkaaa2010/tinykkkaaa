package com.tinykkkaaa.platform.core.cache;

import java.util.Date;

public class CacheManager {
	public static String CATALOG_DEFAULT = "CATALOGDEFAULT";
	public static Date EXPIRY_DEFAULT = new Date(1000*60*60*24);
	
	private ICache cache;
	private int corePoolSize;
	private int maximumPoolSize;
	private int keepAliveTime;
	
	/*
	 * 同步放到队列缓存
	 */
	public void put(String key, Object value, String catalog, Date expired){
		cache.put(key, value, catalog, expired);
	}
	
	public void put(String key, Object value, String catalog){
		put(key, value, catalog, CacheManager.EXPIRY_DEFAULT);
	}
	
	public void put(String key, Object value, Date expired){
		put(key, value, CacheManager.CATALOG_DEFAULT, expired);
	}
	
	public void put(String key, Object value){
		put(key, value, CacheManager.CATALOG_DEFAULT, CacheManager.EXPIRY_DEFAULT);
	}
	
	/*
	 * 从缓存中取出
	 */
	public Object get(String key, String catalog){
		return cache.get(key, catalog);
	}
	public Object get(String key){
		return get(key, CacheManager.CATALOG_DEFAULT);
	}
	
	/*
	 * 从缓存中删除
	 */
	public void remove(String key, String catalog){
		cache.remove(key, catalog);
	}
	public void remove(String key){
		remove(key, CacheManager.CATALOG_DEFAULT);
	}
	public void removeWithCatalog(String catalog){
		cache.removeWithCatalog(catalog);
	}
	
	public ICache getCache() {
		return cache;
	}
	public void setCache(ICache cache) {
		this.cache = cache;
	}
	public int getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
	public int getKeepAliveTime() {
		return keepAliveTime;
	}
	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
}
