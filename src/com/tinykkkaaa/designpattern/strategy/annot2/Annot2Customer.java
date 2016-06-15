package com.tinykkkaaa.designpattern.strategy.annot2;

import com.tinykkkaaa.designpattern.strategy.annot2.sale.ISale;

public class Annot2Customer {
	private double totalAccount;
	private double account;
	private ISale sale;
	
	public double lastAccount(){
		return this.sale.sale(account);
	}
	
	public double getTotalAccount(){
		return this.totalAccount;
	}
	
	public double getAccount(){
		return this.account;
	}
	
	public void buy(double account){
		this.account = account;
		this.totalAccount += account;
		
		this.sale = SaleFactory.getInstance().createSale(this);
	}
}
