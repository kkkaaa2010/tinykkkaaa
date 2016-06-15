package com.tinykkkaaa.designpattern.flyweight.hero;

public class LionHero extends AbstractHero {

	@Override
	public String getName() {
		return "lion";
	}

	@Override
	public void initSkills() {
		this.skills[0] = "lion-技能1";
		this.skills[1] = "lion-技能2";
		this.skills[2] = "lion-技能3";
		this.skills[3] = "lion-技能4";
	}

}
