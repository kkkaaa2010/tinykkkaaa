package com.tinykkkaaa.designpattern.prototype;

public class ShallowPrototype implements Cloneable {
	private String name;
	private String value;
	private FieldPrototype1 field;
	
	public ShallowPrototype(String name, String value, FieldPrototype1 field) {
		this.name = name;
		this.value = value;
		this.field = field;
	}
	
	public void change(String name, String value, FieldPrototype1 field) {
		this.name = name;
		this.value = value;
		this.field = field;
	}

	public FieldPrototype1 getField() {
		return field;
	}
	
	public ShallowPrototype clone() {
		Object object = null;
		
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return (ShallowPrototype) object;
	}
	
	@Override
	public String toString() {
		return "name=" + this.name + " -value=" + this.value + " -fieldname=" + this.field.getFieldname();
	}
	
}
