package com.tinykkkaaa.designpattern.command;

import com.tinykkkaaa.designpattern.command.task.BugTaskImpl;
import com.tinykkkaaa.designpattern.command.task.DemandTaskImpl;
import com.tinykkkaaa.designpattern.command.task.ProblemTaskImpl;

/**
 * 命令模式
 * @author Administrator
 *
 定义：在软件系统中，“行为请求者Salesman”与“行为实现者Programmer”通常呈现一种“紧耦合”。
 但在某些场合，比如要对行为进行“记录、撤销/重做、事务”等处理，这种无法抵御变化的紧耦合是不合适的。
 在这种情况下，如何将“行为请求者”与“行为实现者”解耦？将一组行为抽象为对象，实现二者之间的松耦合。这就是命令模式（Command Pattern）。
 
例子：Salesman是业务人员, Programmer是程序猿, 业务人员发出的系统修改bug或新增需求等任务不直接通知程序猿, 而是发送给ProductManager产品经理, 
由产品经理负责将任务分配给相关程序猿, 实现业务人员与程序猿直接解耦, 由产品经理维护任务列表及程序猿列表.

1，程序猿和业务员解耦，不直接打交道。
2，产品经理分担了程序猿的很多潜在任务，比如制定任务优先级，先做哪个后做哪个。
3，程序猿不至于忘掉其中一个任务，因为产品经理那有任务列表的。
4，任务有规划的完成，不至于加班或者说加班太频繁。

用编程的语言来解释命令模式的使用场景或者说可以解决的问题，就是下面几点。
1，希望将行为请求者和行为实现者解耦，不直接打交道。
2，希望分离掉行为请求者一部分的责任，行为请求者只需要将命令发给调用者，不再主动的去让行为实现者产生行为，符合单一职责原则。
3，希望可以控制执行的命令列表，方便记录，撤销/重做以及事务等功能。
4，期待可以将请求排队，有序执行。
5，希望可以将请求组合使用。
 */
public class Test {
	
	public static void main(String[] args) {
		
		Salesman salesmanA = new Salesman("兰州");
		Salesman salesmanB = new Salesman("无锡");
		
		Programmer programmer1 = new Programmer("小张");
		Programmer programmer2 = new Programmer("小王");
		ProductManager pm = new ProductManager(programmer1, programmer2);
		
		salesmanA.setpManager(pm);
		salesmanB.setpManager(pm);
		
		salesmanA.putDemand(new DemandTaskImpl("兰州新增需求"));
		salesmanA.putBug(new BugTaskImpl("bug1"));
		salesmanA.putBug(new BugTaskImpl("bug2"));
		salesmanA.putBug(new BugTaskImpl("bug3"));
		salesmanA.putBug(new BugTaskImpl("bug4"));
		salesmanA.putBug(new BugTaskImpl("bug5"));
		salesmanA.putBug(new BugTaskImpl("bug6"));
		
		salesmanB.putBug(new BugTaskImpl("bug11"));
		salesmanB.putBug(new BugTaskImpl("bug12"));
		salesmanB.putBug(new BugTaskImpl("bug13"));
		salesmanB.putProblem(new ProblemTaskImpl("问题1"));
		salesmanB.putProblem(new ProblemTaskImpl("问题2"));
		
		System.out.println("第1次分配任务:");
		pm.assign();
		pm.printTaskList();
		
		System.out.println("第2次分配任务:");
		pm.assign();
		pm.printTaskList();
		
		System.out.println("第3次分配任务:");
		pm.assign();
		pm.printTaskList();
	}
}
