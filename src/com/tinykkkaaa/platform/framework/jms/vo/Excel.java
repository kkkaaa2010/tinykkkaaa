package com.tinykkkaaa.platform.framework.jms.vo;

import java.io.Serializable;

public class Excel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rootSign;
	private String filePath;
	
	public Excel(String rootSign, String filePath){
		this.rootSign = rootSign;
		this.filePath = filePath;
	}
	
	public String getRootSign() {
		return rootSign;
	}
	public void setRootSign(String rootSign) {
		this.rootSign = rootSign;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
