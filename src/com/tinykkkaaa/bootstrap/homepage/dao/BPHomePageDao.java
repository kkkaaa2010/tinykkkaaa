package com.tinykkkaaa.bootstrap.homepage.dao;

import net.sf.json.JSONArray;

public interface BPHomePageDao {
	public JSONArray getNavItem(String itemParam) throws Exception;
}
