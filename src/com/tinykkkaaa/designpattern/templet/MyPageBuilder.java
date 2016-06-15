package com.tinykkkaaa.designpattern.templet;

public class MyPageBuilder extends AbstractPageBuilder {

	@Override
	protected void appendTitle(StringBuffer stringBuffer) {
		stringBuffer.append("mypage");
	}

	@Override
	protected void appendBody(StringBuffer stringBuffer) {
		stringBuffer.append("<div><h2>MYPAGE</h2></div>");
	}

}
