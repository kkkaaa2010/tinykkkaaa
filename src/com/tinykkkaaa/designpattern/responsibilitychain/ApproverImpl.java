package com.tinykkkaaa.designpattern.responsibilitychain;

public class ApproverImpl implements Approver {
	
	private String name;
	private Approver nextApprover;
	private double authorityFee;
	
	public ApproverImpl(String name, double authorityFee){
		this.name = name;
		this.authorityFee = authorityFee;
	}

	@Override
	public void setNextApprover(Approver approver) {
		this.nextApprover = approver;
	}

	@Override
	public String handleFeeRequest(String user, double fee) {
		if(fee <= this.authorityFee){
			System.out.println("[" + name + "]同意[" + user + "]申请的" + fee + "元费用");
			return "审批通过";
		}else{
			System.out.println("[" + user + "]申请" + fee + "元费用, "+"[" + name + "]没有权限审批");
			return this.nextApprover.handleFeeRequest(user, fee);
		}
	}

	@Override
	public double getAuthority() {
		return this.authorityFee;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
