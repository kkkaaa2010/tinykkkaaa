package com.tinykkkaaa.platform.core.codetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tinykkkaaa.platform.core.metadata.CodeTable;

public class CodeTableDB implements Serializable {

	private static final long serialVersionUID = -1179183049505919805L;
	
	private Map<String, Integer> codeIndex;
	private Map<String, Integer> valueIndex;
	private Integer index = 0;
	private List<Map<String, String>> columData; // dm:mc
	private CodeTable codeTable;
	
	public CodeTableDB(CodeTable codeTable){
		this.codeTable = codeTable;
		this.codeIndex = new HashMap<String, Integer>();
		this.valueIndex = new HashMap<String, Integer>();
		this.columData = new ArrayList<Map<String,String>>();
	}
	
	public void add(Map<String, String> columMap, String code, String value){
		this.codeIndex.put(code, this.index);
		this.valueIndex.put(value, this.index);
		this.columData.add(columMap);
		this.index++;
	}
	
	public List<Map<String, String>> getColumData(){
		return this.columData;
	}
	public CodeTable getCodeTable(){
		return this.codeTable;
	}
	
	public String getCodeValue(String code){
		Integer cindex = this.codeIndex.get(code);
		return this.columData.get(cindex).get(code);
	}
	
	public String getCode(String codeValue){
		Integer vindex = this.valueIndex.get(codeValue);
		Iterator<String> keyIt = this.columData.get(vindex).keySet().iterator();
		if(keyIt.hasNext()){
			return keyIt.next();
		}else{
			return null;
		}
	}
	
}
