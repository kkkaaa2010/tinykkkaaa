package com.tinykkkaaa.designpattern.flyweight.hero;

public class SFHero extends AbstractHero {

	@Override
	public String getName() {
		return "sf";
	}

	@Override
	public void initSkills() {
		this.skills[0] = "sf-技能1";
		this.skills[1] = "sf-技能2";
		this.skills[2] = "sf-技能3";
		this.skills[3] = "sf-技能4";
	}

}
