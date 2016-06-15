package com.tinykkkaaa.designpattern.templet;

/**
 * 方法声明为abstract，子类必须重写实现，方法如appendMeta声明为空，子类可自由选择是否重写实现
 * @author Administrator
 *
 */
public abstract class AbstractPageBuilder implements IPageBuilder {
	
	private static final String DEFAULT_DOCTYPE = "<!DOCTYPE html>";
	
	private StringBuffer htmlBuffer = new StringBuffer();

	@Override
	public String buildHtml() {
		htmlBuffer.append(DEFAULT_DOCTYPE);
		htmlBuffer.append("<html>");
		htmlBuffer.append("<head>");
		
		appendMeta(htmlBuffer);
		htmlBuffer.append("<title>");
		appendTitle(htmlBuffer);
		htmlBuffer.append("</title>");
		appendLink(htmlBuffer);
		appendScript(htmlBuffer);
		
		htmlBuffer.append("</head>");
		htmlBuffer.append("<body>");
		appendBody(htmlBuffer);
		htmlBuffer.append("</body>");
		htmlBuffer.append("</html>");
		
		return htmlBuffer.toString();
	}
	
	protected void appendMeta(StringBuffer stringBuffer){
	}
	protected void appendLink(StringBuffer stringBuffer){
	}
	protected void appendScript(StringBuffer stringBuffer){
	}

	protected abstract void appendTitle(StringBuffer stringBuffer);
	protected abstract void appendBody(StringBuffer stringBuffer);
}
