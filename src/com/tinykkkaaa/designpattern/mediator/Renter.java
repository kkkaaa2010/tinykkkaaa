package com.tinykkkaaa.designpattern.mediator;

public class Renter extends BusinessMan {

	private String rentername;
	
	public Renter(IAgencyCompany company, String rentername) {
		super(company);
		this.rentername = rentername;
	}

	@Override
	public void buy(BusinessMan seller) {
		this.company.buyFromLandlord(this, seller);
	}

	@Override
	public void sell(BusinessMan buyer) {
		this.company.sellToRenter(this, buyer);
	}

	@Override
	public String getManname() {
		return this.rentername;
	}

}
