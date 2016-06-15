package com.tinykkkaaa.designpattern.bridge;

/**
 * 桥接模式
 * @author Administrator
 *
定义：在软件系统中，某些类型由于自身的逻辑，它具有两个或多个维度的变化，那么如何应对这种“多维度的变化”？
如何利用面向对象的技术来使得该类型能够轻松的沿着多个方向进行变化，而又不引入额外的复杂度？
这就要使用Bridge模式。而具体使用的方式，则是将抽象部分与他们的实现部分分离，使得它们都可以独立的变化。

如果不使用：
第一，子类太多。
第二，由于客户端与具体子类的耦合度很高，导致在子类间切换非常繁琐。
第三，如果以后需要扩展子类功能的话，则子类的数目会随着维度以及每一个维度中子类的个数的增加呈几何倍数增长。

优点
第一，子类的数目减少了。
第二，实现部分的切换非常容易，主要表现在抽象部分和实现部分的耦合度很低，因为使用聚合取代了继承。
第三，扩展的时候很简单，可以更好的容纳变化，不论是维度的增加还是每一个维度中子类的个数的增加，都会变得非常简单。

例子：房屋AbstractHouse是所有房屋的抽象类，如果现在有装修风格古典的饭店、装修风格现代的饭店、装修风格古典的住宅、装修风格现代的住宅，
则需要创建4个子类，现在将装修风格及房屋用途这2个变化维度抽取出来，单独实现，再通过在AbstractHouse父类中组装，
则将抽象部分与他们的实现部分分离，使得它们都可以独立的变化。这样可以降低复杂度，同时以后如果还有其他维度的变化也更容易实现，不会造成子类的膨胀。
 */
public class Test {
	
	public static void main(String[] args) {
		IHouseStyle style1 = new ClassicalStyle();
		IRegisterPurpose purpose1 = new RestaurantPurpose();
		AbstractHouse house = new House(style1, purpose1);
		
		house.purpose();
		house.fix();
		
		System.out.println("重新装修-----------------------------");
		house.changeStyle(new ModernStyle());
		house.getHouseInfo();
	}
}
