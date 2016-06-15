package com.tinykkkaaa.platform.core.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataSet implements Iterator<Map<String, Object>> {
	
	private List<Map<String, Object>> datas = null;
	private Map<String, Object> data = null;
	private int index = 0;
	
	public DataSet(){
		datas = new ArrayList<Map<String,Object>>();
		data = new HashMap<String, Object>();
	}
	
	public void addData(Map<String, Object> map){
		this.datas.add(map);
	}
	
	public void setString(String name, String value){
		this.data.put(name.toUpperCase(), value);
	}
	public void setObject(String name,Object value) {
		this.data.put(name.toUpperCase(), value);
	}
	public void setInteger(String name,Integer value) {
		this.data.put(name.toUpperCase(), value);
	}
	public void setDouble(String name,Double value) {
		this.data.put(name.toUpperCase(), value);
	}
	public void setDate(String name,Date value) {
		this.data.put(name.toUpperCase(), value);
	}
	public void setLong(String name,Long value) {
		this.data.put(name.toUpperCase(), value);
	}
	public void setBigDecimal(String name,BigDecimal value) {
		this.data.put(name.toUpperCase(), value);
	}
	
	private void setRowData(){
		this.data = this.datas.get(this.index-1);
	}
	
	public String getString(String name) {
		setRowData();
		String value = (String)this.data.get(name.toUpperCase());
		return value;
	}

	public Integer getInteger(String name) {
		setRowData();
		Integer value = null;
		if(this.data.get(name).getClass() == BigDecimal.class){
			value = ((BigDecimal)this.data.get(name)).intValue();
		}else{
			value = (Integer)this.data.get(name);
		}
		return value;
	}
	
	public Double getDouble(String name) {
		setRowData();
		Double value = null;
		if(this.data.get(name).getClass() == BigDecimal.class){
			value = ((BigDecimal)this.data.get(name)).doubleValue();
		}else{
			value = (Double)this.data.get(name);
		}
		return value;
	}
	public Date getDate(String name) {
		setRowData();
		Date value = (Date)this.data.get(name.toUpperCase());
		return value;
	}
	public Long getLong(String name) {
		setRowData();
		Long value = (Long)this.data.get(name.toUpperCase());
		return value;
	}

	public BigDecimal getBigDecimal(String name) {
		setRowData();
		BigDecimal value = (BigDecimal)this.data.get(name.toUpperCase());
		return value;
	}

	@Override
	public boolean hasNext() {
		if(this.index >= this.datas.size()){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Map<String, Object> next() {
		this.index++;
		if(this.index > this.datas.size()+1){
			this.index = 1;
		}
		if(this.index <= this.datas.size()){
			return this.datas.get(this.index-1);
		}else{
			return null;
		}
	}

	@Override
	public void remove() {
		this.datas.remove(index-1);
		if(index > datas.size()){
			index = datas.size();
		}
	}
	
	public void moveFirst(){
		this.index = 0;
	}
	
	public int size(){
		return this.datas.size();
	}

}
