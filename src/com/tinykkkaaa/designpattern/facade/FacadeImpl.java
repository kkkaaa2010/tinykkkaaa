package com.tinykkkaaa.designpattern.facade;

public class FacadeImpl implements IFacade {
	
	private ISub1 sub1;
	private ISub2 sub2;
	private ISub3 sub3;
	
	public FacadeImpl(){
		this.sub1 = new Sub1Impl();
		this.sub2 = new Sub2Impl();
		this.sub3 = new Sub3Impl();
	}

	@Override
	public void methodSub1() {
		sub1.methodSub1();
	}

	@Override
	public void methodSub2() {
		sub2.methodSub2();
	}

	@Override
	public void methodSub3() {
		sub3.methodSub3();
	}

}
