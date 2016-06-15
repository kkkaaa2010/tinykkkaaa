package com.tinykkkaaa.designpattern.flyweight.hero;

public abstract class AbstractHero {
	protected String heroname;
	protected String[] skills = new String[4];
	
	public AbstractHero(){
		this.heroname = getName();
		initSkills();
	}
	
	public void releaseSkill(int index){
		System.out.println(getName() + "释放" + skills[index] + "技能...");
	}
	
	public void attack(){
		System.out.println(getName() + "进行物理攻击...");
	}
	
	public abstract String getName();
	public abstract void initSkills();
}
