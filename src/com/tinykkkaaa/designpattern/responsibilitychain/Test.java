package com.tinykkkaaa.designpattern.responsibilitychain;

/**
 * 责任链模式
 * @author Administrator
 *
定义：为了避免请求的发送者和接收者之间的耦合关系，使多个接受对象都有机会处理请求。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。

好处：
  1、客户端与具体的处理者解耦，客户端只认识一个Hanlder接口，降低了客户端（即请求发送者）与处理者的耦合度。
  2、客户端和处理者都不关心职责链的具体结构，而是交给职责链的创造者（ApproverManager），
             也正因为如此，当在职责链中添加处理者的时候，这对客户端和处理者来说，都是透明的，二者不知道也不必要知道职责链的变化。

例子：项目申请费用审批流程。当某人提出费用申请的请求后manager.handleFeeRq("小张", 20000)，
该请求会在 psm —> pm -> boss -> bigboss这样一条领导(ApproverImpl)处理链上进行传递，
发出请求的人并不知道谁会来处理他的请求，每个领导会根据自己的职责范围，来判断是处理请求还是把请求交给更高级别的领导，只要有领导处理了，传递就结束了。
 */
public class Test {
	
	public static void main(String[] args) {
		Approver psm = new ApproverImpl("项目软件经理", 1000);
		Approver pm = new ApproverImpl("项目经理", 5000);
		Approver boss = new ApproverImpl("部门老大", 10000);
		Approver bigboss = new ApproverImpl("大老大", 100000);
		
		ApproverManager manager = ApproverManager.getManager();
		manager.addApprover(psm, pm, boss, bigboss);
		manager.handleFeeRq("小张", 20000);
	}
}
