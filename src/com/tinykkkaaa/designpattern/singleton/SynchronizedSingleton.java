package com.tinykkkaaa.designpattern.singleton;


/**
 * 双重加锁
 * @author Administrator
 这种做法与BadSynchronizedSingleton那种最无脑的同步做法相比就要好很多了，
 因为我们只是在当前实例为null，也就是实例还未创建时才进行同步，否则就直接返回，这样就节省了很多无谓的线程等待时间，
 值得注意的是在同步块中，我们再次判断了synchronizedSingleton是否为null，解释下为什么要这样做。

假设我们去掉同步块中的是否为null的判断，有这样一种情况，假设A线程和B线程都在同步块外面判断了synchronizedSingleton为null，
结果A线程首先获得了线程锁，进入了同步块，然后A线程会创造一个实例，此时synchronizedSingleton已经被赋予了实例，A线程退出同步块，
直接返回了第一个创造的实例，此时B线程获得线程锁，也进入同步块，此时A线程其实已经创造好了实例，
B线程正常情况应该直接返回的，但是因为同步块里没有判断是否为null，直接就是一条创建实例的语句，所以B线程也会创造一个实例返回，此时就造成创造了多个实例的情况。
 */
public class SynchronizedSingleton {
	private static SynchronizedSingleton instance;
	private SynchronizedSingleton(){}
	
	public static SynchronizedSingleton getInstance(){
		if(instance == null){
			synchronized (SynchronizedSingleton.class) {
				if(instance == null){
					instance = new SynchronizedSingleton();
				}
			}
		}
		return instance;
	}
}
