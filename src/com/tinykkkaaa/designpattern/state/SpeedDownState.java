package com.tinykkkaaa.designpattern.state;

public class SpeedDownState implements IRunState {

	@Override
	public void run(Hero hero) {
		System.out.println("减速跑动...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hero.setRunstate(Hero.COMMON);
		System.out.println("减速跑动结束，变为正常状态...");
	}

}
