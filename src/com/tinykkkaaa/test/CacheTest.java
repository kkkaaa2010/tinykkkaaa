package com.tinykkkaaa.test;

import com.tinykkkaaa.platform.core.cache.CacheManager;

public class CacheTest {
	public static void main(String[] args) {
		CacheManager cacheManager = (CacheManager) SpringUtil.getBean("cacheManager");
		cacheManager.put("test1", "test11");
		cacheManager.put("test2", "test22");
		System.out.println(cacheManager.get("test1").toString() + cacheManager.get("test2").toString());
	}
}
