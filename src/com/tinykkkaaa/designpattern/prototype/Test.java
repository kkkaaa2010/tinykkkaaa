package com.tinykkkaaa.designpattern.prototype;

/**
 * 原型模式
 * @author Administrator
 *
定义：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
在JAVA语言中使用原型模式是非常简单的，这是因为Object类当中提供了一个本地方法clone，而JAVA中的任何类只要实现了Cloneable标识接口，就可以使用clone方法来进行对象的拷贝。

原型模式常使用于以下场景：
  1、对象的创建非常复杂，可以使用原型模式快捷的创建对象。
  2、在运行过程中不知道对象的具体类型，可使用原型模式创建一个相同类型的对象，或者在运行过程中动态的获取到一个对象的状态。
               
 对于clone方法，它执行的是浅拷贝，也就是说如果是引用类型的属性，则它不会进行拷贝，而是只拷贝引用。
然而如果要实现深度拷贝，则需要将实现了Cloneable接口并重写了clone方法的类中，所有的引用类型也全部实现Cloneable接口并重写clone方法，而且需要将引用类型的属性全部拷贝一遍。

下面我们来看下原型模式的主要优点：
               1、由于clone方法是由虚拟机直接复制内存块执行，所以在速度上比使用new的方式创建对象要快。
               2、可以基于原型，快速的创建一个对象，而无需知道创建的细节。
               3、可以在运行时动态的获取对象的类型以及状态，从而创建一个对象。
然而原型模式的缺点也是相当明显的，主要的缺点就是实现深度拷贝比较困难，需要很多额外的代码量。
不过实际当中我们使用原型模式时，也可以写一个基类实现Cloneable接口重写clone方法，然后让需要具有拷贝功能的子类继承自该类，这是一种节省代码量的常用方式。
 */
public class Test {
	
	public static void main(String[] args) {
		FieldPrototype1 fp1 = new FieldPrototype1();
		fp1.setFieldname("fp1");
		ShallowPrototype sp = new ShallowPrototype("sp", "shallow", fp1);
		//浅拷贝
		ShallowPrototype sp1 = sp.clone();
		
		sp.change("changesp", "changeshallow", fp1);
		fp1.setFieldname("fp2");
		System.out.println("sp=" + sp + " : field=" + sp.getField());
		System.out.println("sp1=" + sp1 + " : field=" + sp1.getField());
		
		System.out.println("----------------------------------------");
		FieldPrototype fp = new FieldPrototype();
		fp.setFieldname("ffp1");
		DeepPrototype dp = new DeepPrototype("dp", "deep", fp);
		//深拷贝
		DeepPrototype dp1 = dp.clone();
		
		dp.change("changedp", "changedeep", fp);
		fp.setFieldname("ffp2");
		System.out.println("dp=" + dp + " : field=" + dp.getField());
		System.out.println("dp1=" + dp1 + " : field=" + dp1.getField());
	}
}
