package com.tinykkkaaa.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

import com.tinykkkaaa.designpattern.flyweight.hero.AbstractHero;
import com.tinykkkaaa.designpattern.flyweight.hero.LionHero;
import com.tinykkkaaa.designpattern.flyweight.hero.SFHero;

public class HeroManager {
	private static HeroManager heromanager = new HeroManager();
	
	private Map<String, AbstractHero> heroMap;
	
	private HeroManager(){
		heroMap = new HashMap<String, AbstractHero>();
	}
	
	public static HeroManager getInstance(){
		return heromanager;
	}
	
	public AbstractHero getHero(String heroname){
		AbstractHero hero = heroMap.get(heroname);
		if(hero == null){
			if(heroname.equals("lion")){
				hero = new LionHero();
			}else if(heroname.equals("sf")){
				hero = new SFHero();
			}
			heroMap.put(heroname, hero);
		}
		return hero;
	}
}
