package com.tinykkkaaa.designpattern.state;

/**
 * 状态模式
 * @author Administrator
 *
定义：当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。

例子：模拟dota中英雄(Hero)的跑动行为,英雄根据不用的状态runstate来切换跑动行为run.

状态模式解决的问题：状态模式主要解决的是当控制一个对象状态的条件表达式过于复杂时的情况。把状态的判断逻辑转移到表示不同状态的一系列类中，可以把复杂的判断逻辑简化。

对比原始例子HeroBak，现在我们使用状态模式之后，有几个明显的优点：
一、我们去掉了if else结构，使得代码的可维护性更强，不易出错，这个优点挺明显，如果试图让你更改跑动的方法，是刚才的一堆if else好改，还是分成了若干个具体的状态类好改呢？答案是显而易见的。
二、使用多态代替了条件判断，这样我们代码的扩展性更强，比如要增加一些状态，假设有加速20%，加速10%，减速10%等等等（这并不是虚构，DOTA当中是真实存在这些状态的），会非常的容易。                
三、状态是可以被共享的，这个在上面的例子当中有体现，看下Hero类当中的四个static final变量就知道了，因为状态类一般是没有自己的内部状态的，所有它只是一个具有行为的对象，因此是可以被共享的。
四、状态的转换更加简单安全，简单体现在状态的分割，因为我们把一堆if else分割成了若干个代码段分别放在几个具体的状态类当中，
        所以转换起来当然更简单，而且每次转换的时候我们只需要关注一个固定的状态到其他状态的转换。
        安全体现在类型安全，我们设置上下文的状态时，必须是状态接口的实现类，而不是原本的一个整数，这可以杜绝魔数以及不正确的状态码。

状态模式适用于某一个对象的行为取决于该对象的状态，并且该对象的状态会在运行时转换，又或者有很多的if else判断，而这些判断只是因为状态不同而不断的切换行为。

状态模式也有它的缺点，不过它的缺点和大多数模式相似，有两点:
  1、会增加的类的数量。
  2、使系统的复杂性增加。
 */
public class Test {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		Hero hero = new Hero();
		hero.startRun();
		hero.setRunstate(hero.SPEEDUP);
		Thread.sleep(5000);
		hero.setRunstate(hero.SPEEDDOWN);
		Thread.sleep(5000);
		hero.stopRunning();
	}
}
