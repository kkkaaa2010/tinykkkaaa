package com.tinykkkaaa.designpattern.decorator;

import com.tinykkkaaa.designpattern.decorator.drink.Milk;

/**
 * 装饰器模式
 * @author Administrator
 *
定义：装饰模式是在不必改变原类文件和使用继承的情况下，动态的扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
装饰器模式是一个可以非常灵活的动态扩展类功能的设计模式，它采用组合的方式取代继承，使得各个功能的扩展更加独立和灵活。
 */
public class Test {
	
	public static void main(String[] args) {
		//牛奶加糖
		SugerDecorator decorator = new SugerDecorator(new Milk(10, "牛奶"));
		System.out.println("饮品信息 : " + decorator.drinkDesciption());
		System.out.println("饮品价格 : " + decorator.cost());
		System.out.println("顾客要求加糖...");
		decorator.addMoney(2);
		System.out.println("饮品信息 : " + decorator.drinkDesciption());
		System.out.println("饮品价格 : " + decorator.cost());
		
		//牛奶加糖, 并且送代金券
		System.out.println("------------------------------------------");
		Suger2Decorator decorator2 = new Suger2Decorator(new SugerDecorator(new Milk(10, "牛奶")));
		System.out.println("饮品信息 : " + decorator2.drinkDesciption());
		System.out.println("饮品价格 : " + decorator2.cost());
		System.out.println("顾客要求加糖, 此时加糖送优惠券...");
		decorator2.addMoney(2);
		System.out.println("饮品信息 : " + decorator2.drinkDesciption());
		System.out.println("饮品价格 : " + decorator2.cost());
		
		//咖啡也可以使用SugerDecorator和Suger2Decorator装饰器, 同时添加其他作料可以创建不同的装饰器, 不再模拟
		//之所以引入Suger2Decorator是为了不在原有的SugerDecorator中添加送代金券代码sendCard，实现开闭原则
	}
	
}
