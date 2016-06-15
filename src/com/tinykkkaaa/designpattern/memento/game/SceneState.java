package com.tinykkkaaa.designpattern.memento.game;

public class SceneState implements Cloneable {
	private int coin;
	private int wood;
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	public SceneState clone(){
		try {
			return (SceneState) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
