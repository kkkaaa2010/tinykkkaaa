package com.tinykkkaaa.designpattern.visitor.bill;

import com.tinykkkaaa.designpattern.visitor.viewer.Viewer;

public interface Bill {
	void accept(Viewer viewer);
}
