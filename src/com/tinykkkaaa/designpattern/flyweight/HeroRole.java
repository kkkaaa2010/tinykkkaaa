package com.tinykkkaaa.designpattern.flyweight;

import com.tinykkkaaa.designpattern.flyweight.hero.AbstractHero;

public class HeroRole {
	private AbstractHero hero;
	
	private int HP;
	
	public HeroRole(AbstractHero hero){
		this.hero = hero;
	}
	
	public void releaseSkill(int index){
		this.hero.releaseSkill(index);
	}
	public void attack(){
		this.hero.attack();
	}

	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
}
