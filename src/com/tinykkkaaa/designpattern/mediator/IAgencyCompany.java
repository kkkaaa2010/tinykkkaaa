package com.tinykkkaaa.designpattern.mediator;

public interface IAgencyCompany {
	void sellToRenter(BusinessMan seller, BusinessMan buyer);
	void buyFromLandlord(BusinessMan buyer, BusinessMan seller);
	void addLandlord(BusinessMan man);
	void addRenter(BusinessMan man);
}
