package com.tinykkkaaa.designpattern.strategy;

import com.tinykkkaaa.designpattern.strategy.annot.AnnotCustomer;
import com.tinykkkaaa.designpattern.strategy.annot2.Annot2Customer;

/**
 * 
 * 策略模式
 * @author Administrator
 * 
定义：策略模式定义了一系列的算法，并将每一个算法封装起来，而且使它们还可以相互替换。策略模式让算法独立于使用它的客户而独立变化。

策略模式的使用场景，就是有一系列的可相互替换的算法的时候，我们就可以使用策略模式将这些算法做成接口的实现，
并让我们依赖于算法的类依赖于抽象的算法接口，这样可以彻底消除类与具体算法之间的耦合。

例子：模拟商场会员买东西打折,1000元以下不打折, 1000-3000是vip, 以此类推

注： 1.annot包下是使用注解方式来消除SaleFactory中的ifelse逻辑判断代码
   annot.bak包下为SaleFactory原作者代码，与现在版本的不用是，saleList采用扫描包的方式，现在我的是写死的
   2.annot2包下支持多个打折活动（多个策略重叠）, 如下场景：
                假设到端午节了，我们商店要采取满1000返200，满2000返400的方式，并且原有的打折还要继续，这就相当于将返现金的活动与打折重叠计算了。
 */
public class Test {

	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.buy(500);
		System.out.println("需要付款： " + customer.lastAccount() + "元");
		customer.buy(1500);
		System.out.println("需要付款： " + customer.lastAccount() + "元");
		customer.buy(3100);
		System.out.println("需要付款： " + customer.lastAccount() + "元");
		
		AnnotCustomer annotCustomer = new AnnotCustomer();
		annotCustomer.buy(500);
		System.out.println("(注解方式来消除ifelse逻辑判断代码)需要付款： " + annotCustomer.lastAccount() + "元");
		annotCustomer.buy(1500);
		System.out.println("(注解方式来消除ifelse逻辑判断代码)需要付款： " + annotCustomer.lastAccount() + "元");
		annotCustomer.buy(3100);
		System.out.println("(注解方式来消除ifelse逻辑判断代码)需要付款： " + annotCustomer.lastAccount() + "元");
		
		Annot2Customer annot2Customer = new Annot2Customer();
		annot2Customer.buy(500);
		System.out.println("(多个策略重叠)需要付款： " + annot2Customer.lastAccount() + "元");
		annot2Customer.buy(1500);
		System.out.println("(多个策略重叠)需要付款： " + annot2Customer.lastAccount() + "元");
		annot2Customer.buy(3100);
		System.out.println("(多个策略重叠)需要付款： " + annot2Customer.lastAccount() + "元");
	}

}
