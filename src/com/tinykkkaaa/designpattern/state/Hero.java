package com.tinykkkaaa.designpattern.state;

public class Hero {
	public static final IRunState COMMON = new CommonState();//正常状态 
	public static final IRunState SPEEDUP = new SpeedUpState();//加速
	public static final IRunState SPEEDDOWN = new SpeedDownState();//减速
	
	private IRunState runstate = COMMON;//默认状态为正常
	private Thread runThread;//奔跑线程
	
	public void setRunstate(IRunState runstate) {
		this.runstate = runstate;
	}
	
	private boolean isRunning(){
		return runThread!=null && !runThread.isInterrupted();
	}
	
	public void stopRunning(){
		if(isRunning()){
			runThread.interrupt();
		}
		System.out.println("停止跑动...");
	}
	
	public void startRun() {
		if(isRunning()){
			return;
		}
		
		final Hero hero = this;
		runThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!runThread.isInterrupted()){
					runstate.run(hero);
				}
			}
		});
		
		System.out.println("开始跑动...");
		runThread.start();
	}
	
}
