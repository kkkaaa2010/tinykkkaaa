package com.tinykkkaaa.designpattern.singleton;

/**
 * 饿汉式
 * @author Administrator
上述方式与PerfectSingleton给出的方式类似，只不过没有经过内部类处理，
这种方式最主要的缺点就是一旦我访问了Singleton的任何其他的静态域，就会造成实例的初始化，而事实是可能我们从始至终就没有使用这个实例，造成内存的浪费。
不过在有些时候，直接初始化单例的实例也无伤大雅，对项目几乎没什么影响，比如我们在应用启动时就需要加载的配置文件等，就可以采取这种方式去保证单例。
 */
public class HungryManSingleton {
	private static HungryManSingleton instance = new HungryManSingleton();
	
	private HungryManSingleton(){}
	
	public static HungryManSingleton getInstance(){
		return instance;
	}
}
