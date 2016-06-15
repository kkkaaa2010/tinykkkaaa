package com.tinykkkaaa.designpattern.strategy.annot;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.tinykkkaaa.designpattern.strategy.annot.AnnotCustomer;
import com.tinykkkaaa.designpattern.strategy.annot.sale.CommonSale;
import com.tinykkkaaa.designpattern.strategy.annot.sale.GoldSale;
import com.tinykkkaaa.designpattern.strategy.annot.sale.ISale;
import com.tinykkkaaa.designpattern.strategy.annot.sale.SuperSale;
import com.tinykkkaaa.designpattern.strategy.annot.sale.TotalValidRegion;
import com.tinykkkaaa.designpattern.strategy.annot.sale.VipSale;

public class SaleFactory {
	private List<Class<? extends ISale>> saleList; //策略列表
	
	private SaleFactory(){
		init();
	};
	
	private static class SaleFactoryInstance{
		private static SaleFactory instance = new SaleFactory();
	}
	
	public static SaleFactory getInstance(){
		return SaleFactoryInstance.instance;
	}
	
	//初始化策略列表
	public void init(){
		saleList = new ArrayList<Class<? extends ISale>>();
		saleList.add(CommonSale.class);
		saleList.add(VipSale.class);
		saleList.add(SuperSale.class);
		saleList.add(GoldSale.class);
	}
	
	public ISale createSale(AnnotCustomer customer){
		for(Class<? extends ISale> clazz : saleList){
			TotalValidRegion totalValidRegion = handleAnnotation(clazz);
			if(customer.getTotalAccount() > totalValidRegion.min()
			   && customer.getTotalAccount() < totalValidRegion.max()){
				try {
					return clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public TotalValidRegion handleAnnotation(Class<? extends ISale> clazz){
		Annotation[] annotations = clazz.getAnnotations();
		if(annotations == null || annotations.length == 0){
			return null;
		}
		for(Annotation annotation : annotations){
			if(annotation instanceof TotalValidRegion){
				return (TotalValidRegion) annotation;
			}
		}
		return null;
	}
}
