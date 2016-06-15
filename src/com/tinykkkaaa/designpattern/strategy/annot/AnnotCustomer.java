package com.tinykkkaaa.designpattern.strategy.annot;

import com.tinykkkaaa.designpattern.strategy.annot.sale.ISale;

public class AnnotCustomer {
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
		
		this.sale = SaleFactory.getInstance().createSale(this);
	}
}
