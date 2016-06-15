package com.tinykkkaaa.designpattern.memento.game;

public class HeroState implements Cloneable {
	private int HP;
	private int MP;
	
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getMP() {
		return MP;
	}
	public void setMP(int mP) {
		MP = mP;
	}
	
	public HeroState clone(){
		try {
			return (HeroState) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
