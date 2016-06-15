package com.tinykkkaaa.designpattern.mediator;

public abstract class BusinessMan {
	
	protected IAgencyCompany company;
	
	public BusinessMan(IAgencyCompany company){
		this.company = company;
	}
	
	public abstract void buy(BusinessMan seller);
	public abstract void sell(BusinessMan buyer);
	
	public abstract String getManname();
}
