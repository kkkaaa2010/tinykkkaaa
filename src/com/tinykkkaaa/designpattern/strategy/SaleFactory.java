package com.tinykkkaaa.designpattern.strategy;

import com.tinykkkaaa.designpattern.strategy.sale.CommonSale;
import com.tinykkkaaa.designpattern.strategy.sale.GoldSale;
import com.tinykkkaaa.designpattern.strategy.sale.ISale;
import com.tinykkkaaa.designpattern.strategy.sale.SuperSale;
import com.tinykkkaaa.designpattern.strategy.sale.VipSale;

public class SaleFactory {
	
	private SaleFactory(){}
	
	public static ISale createSale(Customer customer){
		ISale sale = null;
		if(customer.getTotalAccount() > 5000){
			sale =  new GoldSale();
		}else if(customer.getTotalAccount() > 3000){
			sale =  new SuperSale();
		}else if(customer.getTotalAccount() > 1000){
			sale = new VipSale();
		}else{
			sale = new CommonSale();
		}
		return sale;
	}
}
