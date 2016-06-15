package com.tinykkkaaa.designpattern.prototype;

public class DeepPrototype implements Cloneable {
	private String name;
	private String value;
	private FieldPrototype field;
	
	public DeepPrototype(String name, String value, FieldPrototype field) {
		this.name = name;
		this.value = value;
		this.field = field;
	}
	
	public void change(String name, String value, FieldPrototype field) {
		this.name = name;
		this.value = value;
		this.field = field;
	}

	public FieldPrototype getField() {
		return field;
	}
	
	public DeepPrototype clone() {
		Object object = null;
		
		try {
			object = super.clone();
			((DeepPrototype)object).field = this.field.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return (DeepPrototype) object;
	}
	
	@Override
	public String toString() {
		return "name=" + this.name + " -value=" + this.value + " -fieldname=" + this.field.getFieldname();
	}
	
}
