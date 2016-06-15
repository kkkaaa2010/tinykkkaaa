package com.tinykkkaaa.designpattern.memento.game;

/**
 * 游戏备忘录角色
 */
public class GameMemento implements Cloneable {
	private HeroState hstate;
	private SceneState sstate;
	
	public GameMemento(HeroState hstate, SceneState sstate){
		this.hstate = hstate.clone();
		this.sstate = sstate.clone();
	}
	
	public HeroState getHeroState(){
		return this.hstate;
	}
	
	public SceneState getSceneState(){
		return this.sstate;
	}
}
