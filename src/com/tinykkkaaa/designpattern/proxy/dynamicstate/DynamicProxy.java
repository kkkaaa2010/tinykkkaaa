package com.tinykkkaaa.designpattern.proxy.dynamicstate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author Administrator
1. 编写调用处理类实现InvocationHandler接口，将需要代理的类（简称被代理类）作为调用处理类的属性（可在构造方法里初始化），为invoke()方法做准备
2. 在调用处理类中重写invoke()方法，加入method.invoke(被代理类, args)，实现被代理类方法回调
3. Proxy.newProxyInstance(被代理类类加载器, 被代理类所有实现的接口, 调用处理类)，生成动态代理类
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object target;
	
	public DynamicProxy(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(this.target, args);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("---UserServiceProxy.before()---");
	}
	private void after(){
		System.out.println("---UserServiceProxy.after()---");
	}
	
	public Object getProxy(){
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(), 
				this);
	}

}
