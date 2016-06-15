package com.tinykkkaaa.designpattern.prototype;

public class FieldPrototype implements Cloneable {
	private String fieldname;

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	
	public FieldPrototype clone() {
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (FieldPrototype) object;
	}
}
