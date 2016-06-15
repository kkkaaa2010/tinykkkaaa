package com.tinykkkaaa.designpattern.strategy;

import com.tinykkkaaa.designpattern.strategy.sale.ISale;

public class Customer {
	private double totalAccount;
	private double account;
	private ISale sale;
	
	public double lastAccount(){
		return this.sale.sale(account);
	}
	
	public double getTotalAccount(){
		return this.totalAccount;
	}
	
	public void buy(double account){
		this.account = account;
		this.totalAccount += account;
		
		this.sale = SaleFactory.createSale(this);
	}
}
