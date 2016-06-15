package com.tinykkkaaa.designpattern.responsibilitychain;

public interface Approver {
	void setNextApprover(Approver approver);
	String handleFeeRequest(String user, double fee);
	double getAuthority();
	String getName();
}
