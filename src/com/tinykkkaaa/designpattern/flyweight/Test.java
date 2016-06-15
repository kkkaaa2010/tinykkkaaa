package com.tinykkkaaa.designpattern.flyweight;

/**
 * 享元模式
 * @author Administrator
 *
定义：享元模式（英语：Flyweight Pattern）是一种软件设计模式。
它使用共享物件，用来尽可能减少内存使用量以及分享资讯给尽可能多的相似物件；
它适合用于当大量物件只是重复因而导致无法令人接受的使用大量内存。
通常物件中的部分状态是可以分享。常见做法是把它们放在外部数据结构，当需要使用时再将它们传递给享元。

享元模式强调内部状态(heroname, skills)和外部状态(hp)，内部状态则是可以共享的状态，外部状态则是随外部环境而变化的状态，是无法共享的状态。

例子：创建英雄时，英雄名称及英雄技能是可以共享的，而不同的客户端使用英雄时，血量hp是不同的。
所以将hp抽到英雄类AbstractHero外部，AbstractHero只保留heroname, skills。同时使用HeroManager对AbstractHero进行管理（类似工厂方法）。
 */
public class Test {
	
	public static void main(String[] args) {
		HeroManager hmanager = HeroManager.getInstance();
		HeroRole rsf1 = new HeroRole(hmanager.getHero("sf"));
		HeroRole rsf2 = new HeroRole(hmanager.getHero("sf"));
		
		HeroRole rlion1 = new HeroRole(hmanager.getHero("lion"));
		HeroRole rlion2 = new HeroRole(hmanager.getHero("lion"));
		
		rsf1.attack();
		rsf2.attack();
		rlion1.releaseSkill(0);
		rlion2.releaseSkill(1);
	}
}
