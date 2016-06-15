package com.tinykkkaaa.designpattern.singleton;

/**
 * @author Administrator
 
SynchronizedSingleton看起来已经很棒了，如果我们深入到JVM中去探索上面这段代码，它就有可能（注意，只是有可能）是有问题的。
 因为虚拟机在执行创建实例的这一步操作的时候，其实是分了好几步去进行的，也就是说创建一个新的对象并非是原子性操作。在有些JVM中上述做法是没有问题的，但是有些情况下是会造成莫名的错误。
首先要明白在JVM创建新的对象时，主要要经过三步。
1.分配内存
2.初始化构造器
3.将对象指向分配的内存的地址
这种顺序在上述双重加锁的方式是没有问题的，因为这种情况下JVM是完成了整个对象的构造才将内存的地址交给了对象。
但是如果2和3步骤是相反的（2和3可能是相反的是因为JVM会针对字节码进行调优，而其中的一项调优便是调整指令的执行顺序），就会出现问题了。
因为这时将会先将内存地址赋给对象，针对上述的双重加锁，就是说先将分配好的内存地址指给instance，然后再进行初始化构造器，
这时候后面的线程去请求getInstance方法时，会认为synchronizedSingleton对象已经实例化了，直接返回一个引用。
如果在初始化构造器之前，这个线程使用了instance，就会产生莫名的错误。

PerfectSingleton这种方式为何会避免了上面莫名的错误，
主要是因为一个类的静态属性只会在第一次加载类时初始化，这是JVM帮我们保证的，所以我们无需担心并发访问的问题。
所以在初始化进行一半的时候，别的线程是无法使用的，因为JVM会帮我们强行同步这个过程。另外由于静态变量只初始化一次，所以instance仍然是单例的。
其主要原理为：Java中静态内部类可以访问其外部类的成员属性和方法，同时，静态内部类只有当被调用的时候才开始首次被加载，利用此特性，可以实现懒汉式。
 */
public class PerfectSingleton {
	private PerfectSingleton(){}
	
	private static class PerfectSingletonInstance{
		static PerfectSingleton instance = new PerfectSingleton();
	}
	
	public static PerfectSingleton getInstance(){
		return PerfectSingletonInstance.instance;
	}
}
