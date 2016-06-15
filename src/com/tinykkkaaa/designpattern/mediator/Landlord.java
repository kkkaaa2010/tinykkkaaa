package com.tinykkkaaa.designpattern.mediator;

public class Landlord extends BusinessMan {
	
	private String landlordname;

	public Landlord(IAgencyCompany company, String landlordname) {
		super(company);
		this.landlordname = landlordname;
	}

	@Override
	public void buy(BusinessMan seller) {
		this.company.buyFromLandlord(this, seller);
	}

	@Override
	public void sell(BusinessMan buyer) {
		this.company.sellToRenter(this, buyer);
	}

	public String getLandlordname() {
		return this.landlordname;
	}

	@Override
	public String getManname() {
		return this.landlordname;
	}
}
