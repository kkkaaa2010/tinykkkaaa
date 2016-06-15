package com.tinykkkaaa.designpattern.visitor.viewer;

import com.tinykkkaaa.designpattern.visitor.bill.ConsumeBill;
import com.tinykkkaaa.designpattern.visitor.bill.IncomeBill;

public class BossViewer extends AbstractViewer {
	
	private double totalConsume;
	private double totalIncome;

	@Override
	public void viewConsumeBill(ConsumeBill bill) {
		this.totalConsume += bill.getAmount();
	}

	@Override
	public void viewIncomeBill(IncomeBill bill) {
		this.totalIncome += bill.getAmount();
	}
	
	public double getTotalIncome() {
		System.out.println("老板查看一共收入多少，数目是：" + this.totalIncome);
		return totalIncome;
	}

	public double getTotalConsume() {
		System.out.println("老板查看一共花费多少，数目是：" + this.totalConsume);
		return totalConsume;
	}

}
