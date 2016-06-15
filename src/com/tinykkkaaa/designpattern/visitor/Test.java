package com.tinykkkaaa.designpattern.visitor;

import com.tinykkkaaa.designpattern.visitor.bill.ConsumeBill;
import com.tinykkkaaa.designpattern.visitor.bill.IncomeBill;
import com.tinykkkaaa.designpattern.visitor.viewer.BossViewer;
import com.tinykkkaaa.designpattern.visitor.viewer.CFOViewer;
import com.tinykkkaaa.designpattern.visitor.viewer.CPAViewer;
import com.tinykkkaaa.designpattern.visitor.viewer.Viewer;

/**
 * 访问者模式
 * @author Administrator
 *
定义：表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素类的前提下定义作用于这些元素的新操作。

例子：账单包含收入IncomeBill与支出ConsumeBill两种，使用AccountBook保存账单billList，同时定义对数据结构billList的遍历操作show。
           同时对于不用的访问者(BossViewer,CPAViewer,CFOViewer)定义对账单的不用操作。

 访问者模式的几个特点：
                 1、访问者模式把数据结构和作用于结构上的操作解耦合，使得操作集合可相对自由地演化。
                 2、访问者模式适用于数据结构相对稳定算法又易变化的系统。因为访问者模式使得算法操作增加变得容易。若系统数据结构对象易于变化，经常有新的数据对象增加进来，则不适合使用访问者模式。
                 3、访问者模式的优点是增加操作很容易，因为增加操作意味着增加新的访问者。访问者模式将有关行为集中到一个访问者对象中，其改变不影响系统数据结构。其缺点就是增加新的数据结构很困难。
                 第一点，数据结构和作用于结构上的操作解耦合，使操作集合可以相对自由的演化，这在上面的例子当中指的是，我们把账本以及账本的元素与查看账本的人解耦，使得这些访问者的行为可以相对独立的变化，这点其实并不难理解。
                 这一点其实说的是访问者模式的优点。
                 至于剩下的两点，开始提到访问者模式适用于数据结构相对稳定，而算法行为又易变化的系统，这点不难理解，试想一下，如果账本结构不稳定，经常有元素加进来，那么假设有了第三种非支出也非收入的单子，
                 那我们需要做以下两件事。
                 1）添加一个类ABill，实现Bill接口。
                 2）在AccountBookViewer接口中添加一个方法view(ABill bill)，并且在所有AccountBookViewer接口的实现类中都增加view(ABill bill)方法的具体实现。
                 这其中第一件事并不难，而且也符合开闭原则，但是第二件事就值得商榷了。它修改了抽象，导致所有细节都跟着变化，这完全破坏了开闭原则。所以第二点说使用访问者模式的前提是数据结构相对稳定也就不奇怪了。
                 然而对于算法操作，在访问者模式的使用下，我们可以自由的添加，这个在上面已经提及到，也就是说我们如果要增加查看账本的类，是非常简单的，我们只需要写一个类去实现AccountBookViewer接口，这是开闭原则的完美诠释。
                 访问者模式中，元素的添加会破坏开闭原则，访问者的添加又符合开闭原则，所以有文献称该模式是倾斜的开闭原则，即一边是符合开闭原则的，一边又是破坏了开闭原则的，有点倾斜的感觉。
                 
                 从上面的描述，我们可以大致总结出访问者模式的优缺点以及适用性。
                 优点：
                 1、使得数据结构和作用于结构上的操作解耦，使得操作集合可以独立变化。
                 2、添加新的操作或者说访问者会非常容易。
                 3、将对各个元素的一组操作集中在一个访问者类当中。
                 4、使得类层次结构不改变的情况下，可以针对各个层次做出不同的操作，而不影响类层次结构的完整性。
                 5、可以跨越类层次结构，访问不同层次的元素类，做出相应的操作。
                 缺点：
                 1、增加新的元素会非常困难。
                 2、实现起来比较复杂，会增加系统的复杂性。
                 3、破坏封装，如果将访问行为放在各个元素中，则可以不暴露元素的内部结构和状态，但使用访问者模式的时候，为了让访问者能获取到所关心的信息，元素类不得不暴露出一些内部的状态和结构，
                                                                就像收入和支出类必须提供访问金额和单子的项目的方法一样。
                 适用性：
                 1、数据结构稳定，作用于数据结构的操作经常变化的时候。
                 2、当一个数据结构中，一些元素类需要负责与其不相关的操作的时候，为了将这些操作分离出去，以减少这些元素类的职责时，可以使用访问者模式。
                 3、有时在对数据结构上的元素进行操作的时候，需要区分具体的类型，这时使用访问者模式可以针对不同的类型，在访问者类中定义不同的操作，从而去除掉类型判断。
 */
public class Test {
	
	public static void main(String[] args) {
		AccountBook ab = new AccountBook();
		ab.addBill(new IncomeBill(3500, "项目1"));
		ab.addBill(new IncomeBill(5500, "项目2"));
		
		ab.addBill(new ConsumeBill(3500, "工资"));
		ab.addBill(new ConsumeBill(1000, "材料费"));
		
		Viewer boss = new BossViewer();
		Viewer cpa = new CPAViewer();
		Viewer cfo = new CFOViewer();
		
		ab.show(boss);
		ab.show(cpa);
		ab.show(cfo);
		
		((BossViewer)boss).getTotalConsume();
		((BossViewer)boss).getTotalIncome();
	}
}
