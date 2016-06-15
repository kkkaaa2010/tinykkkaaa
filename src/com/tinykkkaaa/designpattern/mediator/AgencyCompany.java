package com.tinykkkaaa.designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

public class AgencyCompany implements IAgencyCompany {
	
	private List<BusinessMan> landlordList;
	private List<BusinessMan> renterList;
	
	public AgencyCompany(){
		this.landlordList = new ArrayList<BusinessMan>();
		this.renterList = new ArrayList<BusinessMan>();
	}
	
	@Override
	public void addLandlord(BusinessMan man){
		this.landlordList.add(man);
	}
	
	@Override
	public void addRenter(BusinessMan man){
		this.renterList.add(man);
	}

	@Override
	public void sellToRenter(BusinessMan seller, BusinessMan buyer) {
		if(!(seller instanceof Landlord)){
			System.out.println(seller.getManname() + "不是房主.");
		}else if(!(buyer instanceof Renter)){
			System.out.println(buyer.getManname() + "不是租客.");
		}else if(!landlordList.contains(seller)){
			System.out.println(seller.getManname() + "没有加入中介公司.");
		}else if(!renterList.contains(buyer)){
			System.out.println(buyer.getManname() + "没有加入中介公司.");
		}else{
			System.out.println(seller.getManname() + "通过中介公司将房子卖给" + buyer.getManname());
		}
	}

	@Override
	public void buyFromLandlord(BusinessMan buyer, BusinessMan seller) {
		if(!(seller instanceof Landlord)){
			System.out.println(seller.getManname() + "不是房主.");
		}else if(!(buyer instanceof Renter)){
			System.out.println(buyer.getManname() + "不是租客.");
		}else if(!landlordList.contains(seller)){
			System.out.println(seller.getManname() + "没有加入中介公司.");
		}else if(!renterList.contains(buyer)){
			System.out.println(buyer.getManname() + "没有加入中介公司.");
		}else{
			System.out.println(buyer.getManname() + "通过中介公司买了" + seller.getManname() + "的房子");
		}
	}

}
