package com.tinykkkaaa.designpattern.bridge;

public abstract class AbstractHouse {
	private IHouseStyle houseStyle;
	private IRegisterPurpose registerPurpose;
	
	public AbstractHouse(IHouseStyle houseStyle, IRegisterPurpose registerPurpose){
		this.houseStyle = houseStyle;
		this.registerPurpose = registerPurpose;
	}
	
	public void purpose(){
		this.registerPurpose.purpose();
	}
	public void fix(){
		this.houseStyle.fix();
	}
	
	public void changeStyle(IHouseStyle houseStyle){
		this.houseStyle = houseStyle;
	}
	public void changePurpose(IRegisterPurpose registerPurpose){
		this.registerPurpose = registerPurpose;
	}
	
	public IHouseStyle getStyle(){
		return this.houseStyle;
	}
	public IRegisterPurpose getPurpose(){
		return this.registerPurpose;
	}
	
	public abstract void getHouseInfo();
}
