package com.tinykkkaaa.designpattern.visitor.bill;

import com.tinykkkaaa.designpattern.visitor.viewer.AbstractViewer;
import com.tinykkkaaa.designpattern.visitor.viewer.Viewer;

public class ConsumeBill extends AbstractBill {

	public ConsumeBill(double amount, String item) {
		super(amount, item);
	}

	@Override
	public void accept(Viewer viewer) {
		if(viewer instanceof AbstractViewer){
			((AbstractViewer)viewer).viewConsumeBill(this);
			return;
		}
		viewer.viewAbstractBill(this);
	}

}
