package com.tinykkkaaa.designpattern.visitor.viewer;

import com.tinykkkaaa.designpattern.visitor.bill.AbstractBill;
import com.tinykkkaaa.designpattern.visitor.bill.ConsumeBill;
import com.tinykkkaaa.designpattern.visitor.bill.IncomeBill;

public abstract class AbstractViewer implements Viewer {
	public abstract void viewConsumeBill(ConsumeBill bill);
	public abstract void viewIncomeBill(IncomeBill bill);
	
	@Override
	public final void viewAbstractBill(AbstractBill bill) {}
}
