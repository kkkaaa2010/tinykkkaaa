package com.tinykkkaaa.designpattern.observer;

import java.util.HashMap;
import java.util.Map;

public class WriterManager {
	private Map<String, Writer> writerMap = new HashMap<String, Writer>();
	
	private WriterManager(){}
	
	private static class WriterManagerInstance{
		private static WriterManager instance = new WriterManager();
	}
	
	public static WriterManager getInstance(){
		return WriterManagerInstance.instance;
	}
	
	public void addWriter(Writer writer){
		writerMap.put(writer.getWriterName(), writer);
	}
	
	public Writer getWriter(String writerName){
		return writerMap.get(writerName);
	}
}
