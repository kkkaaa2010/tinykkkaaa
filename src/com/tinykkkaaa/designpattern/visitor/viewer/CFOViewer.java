package com.tinykkkaaa.designpattern.visitor.viewer;

import com.tinykkkaaa.designpattern.visitor.bill.AbstractBill;

public class CFOViewer implements Viewer {

	@Override
	public void viewAbstractBill(AbstractBill bill) {
		System.out.println("财务主管查看账本时，每一个都核对项目和金额，金额是" + bill.getAmount()
							+ "，项目是" + bill.getItem());
	}

}
