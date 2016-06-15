package com.tinykkkaaa.designpattern.strategy.annot2;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.tinykkkaaa.designpattern.strategy.annot2.sale.*;
import com.tinykkkaaa.designpattern.strategy.annot2.annotation.OnceValidRegion;
import com.tinykkkaaa.designpattern.strategy.annot2.annotation.TotalValidRegion;
import com.tinykkkaaa.designpattern.strategy.annot2.sale.ISale;

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
		
		saleList.add(Once1Sale.class);
		saleList.add(Once4Sale.class);
	}
	
	public ISale createSale(Annot2Customer customer){
		
		// 为了支持优先级排序，我们采用可排序的MAP支持,这个Map是为了储存我们当前策略的运行时类信息
		SortedMap<Integer, Class<? extends ISale>> clazzMap = new TreeMap<Integer, Class<? extends ISale>>();
		
		for(Class<? extends ISale> clazz : saleList){
			Annotation validRegion = handleAnnotation(clazz);
			if (validRegion instanceof TotalValidRegion) {
				TotalValidRegion totalValidRegion = (TotalValidRegion) validRegion;
				// 判断总金额是否在注解的区间
				if (customer.getTotalAccount() > totalValidRegion.value().min()
						&& customer.getTotalAccount() < totalValidRegion.value()
								.max()) {
					clazzMap.put(totalValidRegion.value().order(), clazz);// 将采用的策略放入MAP
				}
			} else if (validRegion instanceof OnceValidRegion) {
				OnceValidRegion onceValidRegion = (OnceValidRegion) validRegion;
				// 判断单次金额是否在注解的区间，注意这次判断的是客户当次消费的金额
				if (customer.getAccount() > onceValidRegion.value().min()
						&& customer.getAccount() < onceValidRegion.value().max()) {
					clazzMap.put(onceValidRegion.value().order(), clazz);// 将采用的策略放入MAP
				}
			}
		}
		return SaleProxy.getProxy(clazzMap);
	}
	
	public Annotation handleAnnotation(Class<? extends ISale> clazz){
		Annotation[] annotations = clazz.getAnnotations();
		if(annotations == null || annotations.length == 0){
			return null;
		}
		for(Annotation annotation : annotations){
			if(annotation instanceof TotalValidRegion || annotation instanceof OnceValidRegion){
				return annotation;
			}
		}
		return null;
	}
}
