package com.tinykkkaaa.designpattern.strategy.annot2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.SortedMap;

import com.tinykkkaaa.designpattern.strategy.annot2.sale.ISale;

public class SaleProxy implements InvocationHandler {
	
	private SortedMap<Integer, Class<? extends ISale>> clazzMap;
	
	public SaleProxy(SortedMap<Integer, Class<? extends ISale>> clazzMap){
		this.clazzMap = clazzMap;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Double result = 0D;
		if (method.getName().equals("sale")) {
			for (Class<? extends ISale> clazz : clazzMap.values()) {
				if (result == 0) {
					result = (Double) method.invoke(clazz.newInstance(), args);
				} else {
					result = (Double) method.invoke(clazz.newInstance(), result);
				}
			}
			return result;
		}
		return null;
	}
	
	public static ISale getProxy(SortedMap<Integer, Class<? extends ISale>> clazzMap){
		return (ISale) Proxy.newProxyInstance(SaleProxy.class.getClassLoader(), new Class<?>[]{ISale.class}, new SaleProxy(clazzMap));
	}

}
