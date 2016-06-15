package com.tinykkkaaa.designpattern.abstractfactory;

import com.tinykkkaaa.designpattern.abstractfactory.factory.FactoryImpl;
import com.tinykkkaaa.designpattern.abstractfactory.factory.IFactory;
import com.tinykkkaaa.designpattern.abstractfactory.product.IProductA;
import com.tinykkkaaa.designpattern.abstractfactory.product.IProductB;

/**
 * 抽象工厂
 * @author Administrator
 * 
定义：为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
抽象工厂模式算是工厂相关模式的终极形态，它与工厂方法唯一的区别就是工厂的接口里是一系列创造抽象产品的方法，而不再是一个，
而相应的，抽象产品也不再是一个了，而是一系列相关的产品。

下面罗列下这三种工厂设计模式依次进化的原因。
1，首先从简单工厂进化到工厂方法，是因为工厂方法弥补了简单工厂对修改开放的弊端，即简单工厂违背了开闭原则。
2，从工厂方法进化到抽象工厂，是因为抽象工厂弥补了工厂方法只能创造一个系列的产品的弊端。
 */
public class AbstractfactoryTest {
	public static void main(String[] args) {
		IFactory factory = new FactoryImpl();
		IProductA productA = factory.createIProductA();
		IProductB productB = factory.createIProductB();
		productA.methodA();
		productB.methodB();
	}
}
