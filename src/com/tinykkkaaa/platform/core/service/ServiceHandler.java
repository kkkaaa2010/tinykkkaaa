package com.tinykkkaaa.platform.core.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;
import com.tinykkkaaa.platform.core.session.Context;

public class ServiceHandler implements InvocationHandler {
	
	private Object target;
	private DataPersistenceObject dpo;
	
	public ServiceHandler(Object target, DataPersistenceObject dpo){
		this.target = target;
		this.dpo = dpo;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//暂时不加入session
		Context.init(null, this.dpo);
		Object obj = null;
		
		try {
			if(this.dpo == null || this.dpo.beginTransition() == DataPersistenceObject.STATE_SUCCESS){
				obj = method.invoke(target, args);
			}
			if(this.dpo != null){
				this.dpo.commitTransition();
			}
		} catch (Exception e) {
			if(this.dpo != null){
				this.dpo.rollbackTransition();
			}
			throw new RuntimeException(e);
		} finally {
			if(this.dpo != null){
				this.dpo.close();
			}
		}
		
		return obj;
	}

}
