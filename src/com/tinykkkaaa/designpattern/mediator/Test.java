package com.tinykkkaaa.designpattern.mediator;

/**
 * 中介者模式
 * @author Administrator
 *
定义：用一个中介对象IAgencyCompany来封装一系列的对象交互，
中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。中介者模式又称为调停者模式，它是一种对象行为型模式。

《中介者模式》针对的问题是，解决一系列对象之间复杂的耦合关系，这一系列对象往往是“多对多”的耦合关系，
《中介者模式》采用一个中介者对象将这一系列对象集中管理，而各个对象也将自己与其它对象的交互行为委托给中介者处理，从而减少这一系列对象之间的耦合。

 它的优点倒是非常明显，清除了一系列对象之间复杂的耦合关系，并且中介者可以控制这一系列对象的行为，统一管理。
《中介者模式》的缺点是，由于中介者负责着一系列对象的交互与控制，所以中介者的类会非常复杂，而且一旦中介者类无法正常工作，那么所有将行为委托给中介者的类都将会出现问题，所以在使用的时候还是要特别小心。

例子：模拟房屋中介公司IAgencyCompany与房东Landlord、租客Renter之间的交易。其中房东与租客之间不直接发生交易，而是通过中介公司完成的。

 */
public class Test {
	
	public static void main(String[] args) {
		IAgencyCompany company = new AgencyCompany();
		
		BusinessMan landlord1 = new Landlord(company, "房主老王");
		BusinessMan landlord2 = new Landlord(company, "房主老张");
		
		BusinessMan renter1 = new Renter(company, "租客小李");
		BusinessMan renter2 = new Renter(company, "租客小刘");
		
		company.addLandlord(landlord1);
		company.addLandlord(landlord2);
		company.addRenter(renter1);
		company.addRenter(renter2);
		
		landlord1.sell(renter1);
		landlord1.sell(renter2);
		renter1.sell(renter2);
		
		renter2.buy(landlord2);
	}
}
