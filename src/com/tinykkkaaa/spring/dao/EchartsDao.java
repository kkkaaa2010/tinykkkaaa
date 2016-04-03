package com.tinykkkaaa.spring.dao;

import java.util.List;

import net.sf.json.JSONArray;

public interface EchartsDao {
	public Object[] queryBarData() throws Exception;
	
	public JSONArray queryPieData() throws Exception;
	
	public List<int[]> queryHeatmapData() throws Exception;
}
