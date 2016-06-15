package com.tinykkkaaa.designpattern.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 *  解释器模式
 * @author Administrator
 *
定义：给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。
使用场景：解释器模式需要解决的是，如果一种特定类型的问题发生的频率足够高，那么可能就值得将该问题的各个实例表述为一个简单语言中的句子。这样就可以构建一个解释器，该解释器通过解释这些句子来解决该问题。

 前面已经提到过解释器模式适用的场景，我们这里结合上面的例子总结一下解释器模式的优点：
                 1、由于我们使用具体的终止符和非终止符去解释文法，所以会比较易于编写。
                 2、可以比较方便的修改和扩展文法规则。
相对于优点来说，它的缺点也非常明显，那就是由于我们几乎针对每一个规则都定义了一个类，所以如果一个文法的规则比较多，那对于文法的维护工作也会变得非常困难。

例子：给定一个加减法字符串，利用解释器对字符串进行识别并计算结果
 */
public class Test {
	public static void main(String[] args) {
		List<String> inputList = new ArrayList<String>();
		// 三个正确的，三个错误的
		inputList.add("1+2+3+4+5+6+7+8+9");
		inputList.add("1-2+3-4+5-6+7-8+9");
		inputList.add("9");
		inputList.add("-1+2+3+5");
		inputList.add("1*2");
		inputList.add("11+2+3+9");

		GrammarParser grammarParser = new GrammarParser();// 语法分析器

		for (String input : inputList) {
			Context context = new Context(input.toCharArray());
			try {
				grammarParser.parse(context);// 语法分析器会调用解释器解释表达式
				System.out.println(input + "=" + context.getResult());
			} catch (Exception e) {
				System.out.println("语法错误，请输入正确的表达式！");
			}
		}
	}
}
