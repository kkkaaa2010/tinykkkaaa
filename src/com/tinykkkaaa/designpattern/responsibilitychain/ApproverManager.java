package com.tinykkkaaa.designpattern.responsibilitychain;

public class ApproverManager {
	private static ApproverManager instance = new ApproverManager();
	
	private Approver headApprover;
	private Approver lastApprover;
	
	private ApproverManager(){}
	
	public static ApproverManager getManager(){
		return instance;
	}
	
	public void addApprover(Approver approver){
		if(headApprover == null){
			headApprover = lastApprover = approver;
		}else{
			lastApprover.setNextApprover(approver);
			lastApprover = approver;
		}
	}
	
	public void addApprover(Approver... approvers){
		for(Approver approver : approvers){
			addApprover(approver);
		}
	}
	
	public String handleFeeRq(String user, double fee){
		return this.headApprover.handleFeeRequest(user, fee);
	}
}
