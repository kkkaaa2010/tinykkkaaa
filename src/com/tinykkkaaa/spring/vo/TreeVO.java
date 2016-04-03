package com.tinykkkaaa.spring.vo;

public class TreeVO {
	private int treeid;
	private int ptreeid;
	private String treename;
	private String url;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTreeid() {
		return treeid;
	}
	public void setTreeid(int treeid) {
		this.treeid = treeid;
	}
	public int getPtreeid() {
		return ptreeid;
	}
	public void setPtreeid(int ptreeid) {
		this.ptreeid = ptreeid;
	}
	public String getTreename() {
		return treename;
	}
	public void setTreename(String treename) {
		this.treename = treename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
