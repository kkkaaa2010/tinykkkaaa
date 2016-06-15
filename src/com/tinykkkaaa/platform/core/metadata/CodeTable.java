package com.tinykkkaaa.platform.core.metadata;

import java.io.Serializable;

public class CodeTable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;					// 代码表逻辑名称
	private String tableName;				// 代码数据表名称
	private String codeColumnName;			// 代码列名
	private String labelColumnName;			// 代码名称列名
	private String parentColumnName;		// 父代码列名
	private String parentName;				// 父代码表定义名称（需先将所有定义初始化并记录parentName，再循环设置所有定义的parent）
	private CodeTable parent;				// 父代码表定义
	private String dataSourceName;			// 数据源名称
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCodeColumnName() {
		return codeColumnName;
	}
	public void setCodeColumnName(String codeColumnName) {
		this.codeColumnName = codeColumnName;
	}
	public String getLabelColumnName() {
		return labelColumnName;
	}
	public void setLabelColumnName(String labelColumnName) {
		this.labelColumnName = labelColumnName;
	}
	public String getParentColumnName() {
		return parentColumnName;
	}
	public void setParentColumnName(String parentColumnName) {
		this.parentColumnName = parentColumnName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public CodeTable getParent() {
		//如果父代码表元数据定义为空，且父代码列名不为空，则认为父代码表元数据定义既当前定义
		return ( parent == null && parentColumnName != null && parentColumnName.length() > 0 )? this : parent;
	}
	public void setParent(CodeTable parent) {
		this.parent = parent;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
}
