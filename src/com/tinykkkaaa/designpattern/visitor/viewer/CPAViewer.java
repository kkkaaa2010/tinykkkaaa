package com.tinykkkaaa.designpattern.visitor.viewer;

import com.tinykkkaaa.designpattern.visitor.bill.ConsumeBill;
import com.tinykkkaaa.designpattern.visitor.bill.IncomeBill;

public class CPAViewer extends AbstractViewer {

	@Override
	public void viewConsumeBill(ConsumeBill bill) {
		if (bill.getItem().equals("工资")) {
			System.out.println("注会查看账本时，如果单子的消费目的是发工资，则注会会查看有没有交个人所得税。");
		}
	}

	@Override
	public void viewIncomeBill(IncomeBill bill) {
		System.out.println("注会查看账本时，只要是收入，注会都要查看公司交税了没。");
	}

}
