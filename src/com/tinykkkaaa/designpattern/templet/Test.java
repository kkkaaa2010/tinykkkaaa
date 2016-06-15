package com.tinykkkaaa.designpattern.templet;

/**
 * 模板方法模式
 * @author Administrator
 *
模板方法模式，这是一个在许多优秀的开源项目中使用最多的一个设计模式，也是非常优秀的一个设计模式。
模板方法模式，一般是为了统一子类的算法实现步骤，所使用的一种手段或者说是方式。它在父类中定义一系列算法的步骤，而将具体的实现都推迟到子类。
                  
  通常情况下，模板方法模式用于定义构建某个对象的步骤与顺序，或者定义一个算法的骨架。
在这里要声明一点，对于模板方法模式，父类提供的构建步骤和顺序或者算法骨架，通常是不希望甚至是不允许子类去覆盖的，
所以在某些场景中，可以直接将父类中提供骨架的方法声明为final类型。
模板方法模式还有一种使用的方式，为了给子类足够的自由度，可以提供一些方法供子类覆盖，去实现一些骨架中不是必须但却可以有自定义实现的步骤。
 */
public class Test {
	public static void main(String[] args) {
		IPageBuilder pageBuilder = new MyPageBuilder();
		System.out.println(pageBuilder.buildHtml());
	}

}
