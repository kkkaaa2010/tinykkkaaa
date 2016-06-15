package com.tinykkkaaa.designpattern.facade;

/**
 * 外观模式
 * @author Administrator
 *
定义：外观模式是软件工程中常用的一种软件设计模式。它为子系统中的一组接口提供一个统一的高层接口。这一接口使得子系统更加容易使用。
与web项目中的service和dao相类似，IFacade相当于service，ISub...相当于dao
 */
public class Test {

	public static void main(String[] args) {
		IFacade facade = new FacadeImpl();
		facade.methodSub1();
		facade.methodSub2();
		facade.methodSub3();
	}
}
