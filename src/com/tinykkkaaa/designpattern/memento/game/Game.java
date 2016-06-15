package com.tinykkkaaa.designpattern.memento.game;

/**
 * 游戏自身（备忘录模式中的发起人，备份的是游戏的状态）
 */
public class Game {
	private HeroState hstate;
	private SceneState sstate;

	public void setHstate(HeroState hstate) {
		this.hstate = hstate;
	}
	public void setSstate(SceneState sstate) {
		this.sstate = sstate;
	}
	
	public GameMemento createGameMemento(){
		return new GameMemento(this.hstate, this.sstate);
	}
	
	public void restore(GameMemento memento){
		this.hstate = memento.getHeroState();
		this.sstate = memento.getSceneState();
	}
	
	public void play(){
		this.hstate.setHP(0);
		this.hstate.setMP(0);
		this.sstate.setCoin(0);
		this.sstate.setWood(0);
	}
	
	public HeroState getHeroState(){
		return this.hstate;
	}
	public SceneState getSceneState(){
		return this.sstate;
	}
}
