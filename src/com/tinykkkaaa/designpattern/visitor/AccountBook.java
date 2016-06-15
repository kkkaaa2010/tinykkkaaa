package com.tinykkkaaa.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

import com.tinykkkaaa.designpattern.visitor.bill.Bill;
import com.tinykkkaaa.designpattern.visitor.viewer.Viewer;

public class AccountBook {
	private List<Bill> billList = new ArrayList<Bill>();
	
	public void addBill(Bill bill){
		this.billList.add(bill);
	}
	
	public void show(Viewer viewer){
		for(Bill bill : billList){
			bill.accept(viewer);
		}
	}
}
