package com.tinykkkaaa.designpattern.memento;

import com.tinykkkaaa.designpattern.memento.game.Game;
import com.tinykkkaaa.designpattern.memento.game.GameMemento;
import com.tinykkkaaa.designpattern.memento.game.HeroState;
import com.tinykkkaaa.designpattern.memento.game.SceneState;

/**
 * 备忘录模式
 * @author Administrator
 *
 定义：在不破坏封闭的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。

优点：
 1、发起人Game备份自己的状态不需要自己管理，可以备份到外部Caretaker，这样可以很好的保持封装的边界，这样做的意义在于可以给外部提供一个简单的操作该对象内部状态的接口。保持封装的边界这应该算是最重要的优点了。
 2、发起人状态的备份与恢复，发起人自身不需要管理与操作，而是由客户端自行按需要处理。
 3、如果发起人的状态出现问题，可以很轻松的恢复。
 
 缺点：
 1、如果全部备份发起人的状态，或者其中有占用内存较大的属性（比如一个长数组），则会让备忘录模式的使用代价昂贵。
 2、由于备份的信息是由发起人自己提供的，所以管理者无法预知备份的信息的大小，所以在客户端使用时，可能一个操作占用了很大的内存，但客户端并不知晓。
 3、当发起人的状态改变的时候，有可能这个状态无效。如果状态改变的成功率不高的话，可以采取“假如”协议模式。“假如”的意思是指，我们将一直假如状态的改变会失败，从而对此做出一系列准备的工作。不过很明显，如果状态改变的成功率很高，则这样做的收益甚微。
 */
public class Test {
	
	public static void main(String[] args) {
		System.out.println("创建游戏...");
		Game game = new Game();
		
		HeroState hstate = new HeroState();
		hstate.setHP(100);
		hstate.setMP(50);
		
		SceneState sstate = new SceneState();
		sstate.setCoin(10000);
		sstate.setWood(100);
		
		game.setHstate(hstate);
		game.setSstate(sstate);
		
		System.out.println("游戏备份...");
		GameMemento gameMemento = game.createGameMemento();
		Caretaker ct = new Caretaker();
		ct.setMemento(gameMemento);
		
		System.out.println("开始游戏，当前英雄生命值:" + game.getHeroState().getHP()
				           + "，魔法值：" + game.getHeroState().getMP());
		game.play();
		System.out.println("游戏结束，当前英雄生命值:" + game.getHeroState().getHP()
		           + "，魔法值：" + game.getHeroState().getMP());
		
		System.out.println("游戏还原...");
		game.restore(ct.getMemento());
		
		System.out.println("当前英雄生命值:" + game.getHeroState().getHP()
		           + "，魔法值：" + game.getHeroState().getMP());
	}
}
