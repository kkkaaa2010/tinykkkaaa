package com.tinykkkaaa.designpattern.adapter;

import com.tinykkkaaa.designpattern.adapter.defaultadapter.DeafPerson;

/**
 * 适配器模式
 * @author Administrator

1. 类适配器(ClassAdapter)：使用对象继承的方式,在适配器中加入父类没有的方法，或者重写父类的方法.
2. 对象适配器(ObjectAdapter)：如果ClassAdapter还要适应另一个父类,这时由于ClassAdapter已经继承了一个父类，无法再继承一个类，此时可将需要适应的父类组合到ClassAdapter中，实现对象适配器.

 总结下两种实现方式的适配器所使用的场景，两者都是为了将已有类的代码复用并且适配到客户端需要的接口上去。
1，第一种类适配器，一般是针对适配目标是接口的情况下使用。
2，第二种对象适配器，一般是针对适配目标是类或者是需要复用的对象多于一个的时候使用，这里再专门提示一下，对象适配器有时候是为了将多个类一起适配，所以才不得不使用组合的方式，
而且我们采用对象适配器的时候，继承也不是必须的，而是根据实际的类之间的关系来进行处理.

 对于第三个缺省适配器(DefaultPersonAdapter)，一般是为了弥补接口过大所犯下的过错，但是也请注意衡量利弊，权衡好以后再考虑是否要使用缺省适配器。
我们来看看缺省适配器的历史来由，最小接口原则，这个原则所表达的思想是说接口的行为应该尽量的少，那么如果没做到的话会产生什么情况吗？
结果就是实现这个接口的子类，很可能出现很多方法是空着的情况，因为你的接口设计的过大，导致接口中原本不该出现的方法出现了，
结果现在子类根本用不上这个方法，但由于JAVA语言规则的原因，实现一个接口必须实现它的全部方法，所以我们的子类不得不被迫写一堆空方法在那，只为了编译通过。

IPerson是一个人的接口，这个接口表示了人可以听说读写，假设有一个聋哑人，好吧，3个方法都要空着了，此时定义DefaultPersonAdapter实现IPerson接口中各个方法（可以提供默认操作及返回值），
然后DeafPerson继承DefaultPersonAdapter就不用重写那3个方法了

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
 首先应该明白一点，适配器模式是补救措施，所以在系统设计过程中请忘掉这个设计模式，这个模式只是在你无可奈何时的补救方式。
 那么我们什么时候使用这个模式呢？
 场景通常情况下是，系统中有一套完整的类结构，而我们需要利用其中某一个类的功能（通俗点说可以说是方法），
 但是我们的客户端只认识另外一个和这个类结构不相关的接口，这时候就是适配器模式发挥的时候了，我们可以将这个现有的类与我们的目标接口进行适配，最终获得一个符合需要的接口并且包含待复用的类的功能的类。 
 */
public class Test {

	public static void main(String[] args) {
		ClassAdapter adapter = new ClassAdapter("张三");
		adapter.printUser();
		adapter.printChangeUser("李四");
		
		ObjectAdapter oadapter = new ObjectAdapter("张三三", "狮子");
		oadapter.printUser();
		oadapter.printAnimal();
		oadapter.printAll();
		
		DeafPerson dp = new DeafPerson();
		dp.write();
	}

}
