package com.tinykkkaaa.spring.service;

public interface EchartsService {
	public Object[] queryBarData() throws Exception;
	
	public Object[] queryPieData() throws Exception;
	
	public Object[] queryHeatmapData() throws Exception;
}
