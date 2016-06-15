package com.tinykkkaaa.designpattern.interpreter;

import java.util.ArrayList;
import java.util.List;

//上下文  
public class Context {

	private int result;// 结果

	private int index;// 当前位置

	private int mark;// 标志位

	private char[] inputChars;// 输入的字符数组

	private List<Integer> operateNumbers = new ArrayList<Integer>(2);// 操作数

	private char operator;// 运算符

	public Context(char[] inputChars) {
		super();
		this.inputChars = inputChars;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean hasNext() {
		return index != inputChars.length;
	}

	public char next() {
		return inputChars[index++];
	}

	public char current() {
		return inputChars[index];
	}

	public List<Integer> getOperateNumbers() {
		return operateNumbers;
	}

	public void setLeftOperateNumber(int operateNumber) {
		this.operateNumbers.add(0, operateNumber);
	}

	public void setRightOperateNumber(int operateNumber) {
		this.operateNumbers.add(1, operateNumber);
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public void mark() {
		mark = index;
	}

	public void reset() {
		index = mark;
	}
}