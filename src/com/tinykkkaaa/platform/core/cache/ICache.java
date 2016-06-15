package com.tinykkkaaa.platform.core.cache;

import java.util.Date;

public interface ICache {
	public Object get(String key, String catalog);
	public void put(String key, Object value, String catalog, Date expired);
	public void remove(String key, String catalog);
	public void removeWithCatalog(String catalog);
}
