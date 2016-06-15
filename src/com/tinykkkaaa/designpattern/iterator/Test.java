package com.tinykkkaaa.designpattern.iterator;

import java.util.Iterator;

/**
 * 迭代器模式
 * @author Administrator
 *
 定义：提供一种方法顺序访问一个聚合对象中各个元素，而又不需暴露该对象的内部表示。

  1、迭代器模式可以提供统一的迭代方式，这个要归功于Iterator接口。
  2、迭代器模式可以在对客户透明的前提下，做出各种不同的迭代方式。
  3、在迭代的时候不需要暴露聚合对象的内部表示，我们只需要认识Iterator即可。
  4、在第1条的前提下，解决了基于数组的迭代方式中重复遍历的问题。
 */
public class Test {
	
	public static void main(String[] args) {
		MyArrList<String> list = new MyArrList<String>();
		list.add("l1");
		list.add("l2");
		list.add("l3");
		list.add("l4");
		list.add("l5");
		
		System.out.println("迭代器:");
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		System.out.println("foreach:");
		for(String item : list){
			System.out.println(item);
		}
	}
}
