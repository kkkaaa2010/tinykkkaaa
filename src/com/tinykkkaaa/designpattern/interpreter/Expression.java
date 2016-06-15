package com.tinykkkaaa.designpattern.interpreter;

//抽象表达式，定义一个解释操作  
public interface Expression {

	void interpreter(Context context);

}