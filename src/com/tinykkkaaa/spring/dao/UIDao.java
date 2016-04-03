package com.tinykkkaaa.spring.dao;

import net.sf.json.JSONArray;

public interface UIDao {
	public JSONArray getNavTree(String ptreeid) throws Exception;
}
