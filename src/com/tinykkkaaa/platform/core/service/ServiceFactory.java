package com.tinykkkaaa.platform.core.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

import com.tinykkkaaa.platform.core.SystemManager;
import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;

public class ServiceFactory {
	
	private static String rulePackage = ".impl."; //扫描实现类(包路径)规则
	private static String ruleClassName = "Impl"; //扫描实现类(类名)规则
	
	private static ServiceFactory instance;
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getService(String serviceName, Class<T> serviceClazz, String daoName, Class<?> daoClazz){
		
		T service = null;
		try {
			String servicePackageName = serviceClazz.getPackage().getName();
			String daoPackageName = daoClazz.getPackage().getName();
			
			Object daoObject = null;
			Constructor<?> daoConstructor = Class.forName(daoPackageName+rulePackage+daoName+ruleClassName)
											.getDeclaredConstructor(new Class[]{DataPersistenceObject.class});
			//持久化事务管理
			DataPersistenceObject dpo = SystemManager.getInstance().getDataPersistenceManager().getDefaultDataPersistenceObject();
			daoObject = daoConstructor.newInstance(new Object[]{dpo});
			
			//Object obj = Class.forName(packageName+rulePackage+serviceName+ruleClassName).newInstance();
			Object serviceObj = null;
			Constructor<?> serviceConstructor = Class.forName(servicePackageName+rulePackage+serviceName+ruleClassName)
											 .getDeclaredConstructor(new Class[]{daoClazz});
			serviceObj = serviceConstructor.newInstance(new Object[]{daoObject});
			
			ServiceHandler handler = new ServiceHandler(serviceObj, dpo);
			service = (T)Proxy.newProxyInstance(serviceObj.getClass().getClassLoader(), serviceObj.getClass().getInterfaces(), handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}
	
	public <T> T getService(Class<T> serviceClazz, Class<?> daoClazz){
		String tempServiceName = serviceClazz.getName();
		String serviceName = tempServiceName.substring(tempServiceName.lastIndexOf(".")+1);
		String tempDaoName = daoClazz.getName();
		String daoName = tempDaoName.substring(tempDaoName.lastIndexOf(".")+1);
		return this.getService(serviceName, serviceClazz, daoName, daoClazz);
	}
}
